package com.zhang.myproject.config;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * create by zhangbo on 2019/11/7 0007
 * <p>
 * HttpTraceRepository实现这个配置,才能暴露出httptrace端点
 */
@Configuration
public class InMemoryHttpTraceRepository implements HttpTraceRepository {

    private int capacity = 100;

    private boolean reverse = true;

    private final List<HttpTrace> traces = new LinkedList<>();

    /**
     * Flag to say that the repository lists traces in reverse order.
     *
     * @param reverse flag value (default true)
     */
    public void setReverse(boolean reverse) {
        synchronized (this.traces) {
            this.reverse = reverse;
        }
    }

    /**
     * Set the capacity of the in-memory repository.
     *
     * @param capacity the capacity
     */
    public void setCapacity(int capacity) {
        synchronized (this.traces) {
            this.capacity = capacity;
        }
    }

    @Override
    public List<HttpTrace> findAll() {
        synchronized (this.traces) {
            return Collections.unmodifiableList(new ArrayList<>(this.traces));
        }
    }

    @Override
    public void add(HttpTrace trace) {
        synchronized (this.traces) {
            while (this.traces.size() >= this.capacity) {
                this.traces.remove(this.reverse ? this.capacity - 1 : 0);
            }
            if (this.reverse) {
                this.traces.add(0, trace);
            } else {
                this.traces.add(trace);
            }
        }
    }

}
