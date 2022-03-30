package com.example.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;
import static lombok.AccessLevel.NONE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter(NONE)
public class Event<K, T> {

  public enum Type {
    CREATE
  }

  private Type eventType;
  private K key;
  private T data;
  private LocalDateTime eventCreatedAt;

  public Event(Type eventType, K key, T data) {
    this.eventType = eventType;
    this.key = key;
    this.data = data;
    this.eventCreatedAt = now();
  }

}
