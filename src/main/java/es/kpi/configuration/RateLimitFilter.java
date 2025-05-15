package es.kpi.configuration;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> buckets = new ConcurrentHashMap<>();


    private Bucket createNewBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.simple(100, Duration.ofMinutes(1))) // 100 req/min
                .build();
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String userAddress = request.getRemoteAddr(); //retieve user Address to identify user and requests

        if (userAddress == null) {
            response.sendError(400, "Missing user ID");
            return;
        }

        Bucket bucket = buckets.computeIfAbsent(userAddress, k -> createNewBucket());

        if (bucket.tryConsume(1)) {
            filterChain.doFilter(request, response);
        } else {
            response.sendError(429, "Too Many Requests");
        }
    }
}
