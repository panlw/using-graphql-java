package xyz.neopan.jdk;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author neo.pan
 * @since 2020/1/28
 */
class CompletableFutureTests {

    @Test
    void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("main.async");
            return 100;
        });
        CompletableFuture<String> f = future
            .thenApplyAsync(x -> {
                final String y = x * 10 + "";
                System.out.println("then.async: " + x + " -> " + y);
                return y;
            });
        assertThat(f.get()).isEqualTo("1000");
    }

    @Test
    void thenCompose() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("main.async");
            return 100;
        });
        CompletableFuture<String> f = future.thenComposeAsync(x -> {
            System.out.println("then.async: " + x + " -> async-fn");
            return CompletableFuture.supplyAsync(() -> {
                final String y = (x * 10) + "";
                System.out.println("then.async -> async: " + x + " -> " + y);
                return y;
            });
        });
        assertThat(f.get()).isEqualTo("1000");
    }

}
