package com.server.order.service.emitter;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class EmitterService {

    private final Queue<SseEmitter> emitters = new ConcurrentLinkedQueue<>();

    public SseEmitter createEmitter() {
        SseEmitter emitter = new SseEmitter();
        this.emitters.add(emitter);

        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> this.emitters.remove(emitter));

        return emitter;
    }

    public void receiveMessage(String message) {
        for (SseEmitter emitter : this.emitters) {
            try {
                emitter.send(message);
            } catch (Exception e) {
                this.emitters.remove(emitter);
            }
        }
    }

}
