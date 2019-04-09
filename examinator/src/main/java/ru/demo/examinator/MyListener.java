package ru.demo.examinator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MyListener {

    @EventListener(RefreshRemoteApplicationEvent.class)
    public void refersh(RefreshRemoteApplicationEvent refreshRemoteApplicationEvent) {
        log.info("refreshRemoteApplicationEvent = " + refreshRemoteApplicationEvent.getDestinationService());
    }
}
