package com.olowolo.benchmark.generationtype;

import com.olowolo.benchmark.generationtype.domain.GenericUser;
import com.olowolo.benchmark.generationtype.domain.IdentityUser;
import com.olowolo.benchmark.generationtype.domain.SequenceOptimizerUser;
import com.olowolo.benchmark.generationtype.domain.SequenceUser;
import com.olowolo.benchmark.generationtype.domain.TableOptimizerUser;
import com.olowolo.benchmark.generationtype.domain.TableUser;
import com.olowolo.benchmark.generationtype.repository.IdentityUserRepository;
import com.olowolo.benchmark.generationtype.repository.SequenceOptimizerUserRepository;
import com.olowolo.benchmark.generationtype.repository.SequenceUserRepository;
import com.olowolo.benchmark.generationtype.repository.TableOptimizerUserRepository;
import com.olowolo.benchmark.generationtype.repository.TableUserRepository;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Threads;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author olOwOlo
 */
@Threads(4)
@Warmup(iterations = 10)
@Measurement(iterations = 30)
@BenchmarkMode(Mode.SingleShotTime)
@State(Scope.Thread)
public class GenerationTypeBenchmark {

  private static final int BATCH_SIZE = 10;
  private static final int ROUND = 10;
  private static final int TOTAL = BATCH_SIZE * ROUND; // TOTAL = 100

  private static ConfigurableApplicationContext context;

  private static IdentityUserRepository identityUserRepository;
  private static SequenceUserRepository sequenceUserRepository;
  private static TableUserRepository tableUserRepository;
  private static SequenceOptimizerUserRepository sequenceOptimizerUserRepository;
  private static TableOptimizerUserRepository tableOptimizerUserRepository;

  @Setup
  public void init() {
    if (context == null) {
      synchronized (GenerationTypeBenchmark.class) {
        if (context == null) {
          SpringApplication app = new SpringApplication(Application.class);
          app.setBannerMode(Banner.Mode.OFF);
          context = app.run("");

          identityUserRepository = context.getBean(IdentityUserRepository.class);
          sequenceUserRepository = context.getBean(SequenceUserRepository.class);
          tableUserRepository = context.getBean(TableUserRepository.class);
          sequenceOptimizerUserRepository = context.getBean(SequenceOptimizerUserRepository.class);
          tableOptimizerUserRepository = context.getBean(TableOptimizerUserRepository.class);

          tableUserRepository.save(new TableUser(null, "init", "init"));
          tableOptimizerUserRepository.save(new TableOptimizerUser(null, "init", "init"));
        }
      }
    }
  }

  @Benchmark
  public void identityBenchmark() {
    genericBenchmark(identityUserRepository, IdentityUser.class);
  }

  @Benchmark
  public void identityBatchBenchmark() {
    genericBatchBenchmark(identityUserRepository, IdentityUser.class);
  }

  @Benchmark
  public void sequenceBenchmark() {
    genericBenchmark(sequenceUserRepository, SequenceUser.class);
  }

  @Benchmark
  public void sequenceBatchBenchmark() {
    genericBatchBenchmark(sequenceUserRepository, SequenceUser.class);
  }

  @Warmup(iterations = 5)
  @Measurement(iterations = 5)
  @Benchmark
  public void tableBenchmark() {
    genericBenchmark(tableUserRepository, TableUser.class);
  }

  @Warmup(iterations = 5)
  @Measurement(iterations = 5)
  @Benchmark
  public void tableBatchBenchmark() {
    genericBatchBenchmark(tableUserRepository, TableUser.class);
  }

  @Benchmark
  public void sequenceOptimizerBenchmark() {
    genericBenchmark(sequenceOptimizerUserRepository, SequenceOptimizerUser.class);
  }

  @Benchmark
  public void sequenceOptimizerBatchBenchmark() {
    genericBatchBenchmark(sequenceOptimizerUserRepository, SequenceOptimizerUser.class);
  }

  @Benchmark
  public void tableOptimizerBenchmark() {
    genericBenchmark(tableOptimizerUserRepository, TableOptimizerUser.class);
  }

  @Benchmark
  public void tableOptimizerBatchBenchmark() {
    genericBatchBenchmark(tableOptimizerUserRepository, TableOptimizerUser.class);
  }

  private <T extends GenericUser> void genericBenchmark(JpaRepository repository, Class<T> clazz) {
    try {
      for (int i = 0; i < TOTAL; i++) {
        T user = clazz.getDeclaredConstructor().newInstance();
        user.setFirstName("firstName" + i);
        user.setLastName("lastName" + i);
        repository.save(user);
      }
    } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  private <T extends GenericUser> void genericBatchBenchmark(JpaRepository repository, Class<T> clazz) {
    try {
      for (int i = 0; i < ROUND; i++) {
        List<T> users = new ArrayList<>();
        for (int j = 0; j < BATCH_SIZE; j++) {
          T user = clazz.getDeclaredConstructor().newInstance();
          user.setFirstName("batchFirstName" + j);
          user.setLastName("batchLastName" + j);
          users.add(user);
        }
        repository.saveAll(users);
      }
    } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) throws RunnerException {
    Options opt = new OptionsBuilder()
        .include(GenerationTypeBenchmark.class.getSimpleName())
        .forks(1)
        .shouldFailOnError(true)
        .build();

    new Runner(opt).run();
  }
}
