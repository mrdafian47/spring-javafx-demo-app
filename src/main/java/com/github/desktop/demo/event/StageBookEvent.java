package com.github.desktop.demo.event;

import com.github.desktop.demo.entity.BookEntity;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class StageBookEvent extends ApplicationEvent {

    private final BookEntity entity;

    public StageBookEvent(Object source) {
        super(source);
        this.entity = (BookEntity) source;
    }
}
