package ru.tinkoff.edu.configuration.application;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;
import ru.tinkoff.edu.configuration.sheduler.Scheduler;

@Getter
@Setter
@ToString
@Validated
public class ApplicationConfig { @NotNull String test; @NotNull Scheduler scheduler;}

