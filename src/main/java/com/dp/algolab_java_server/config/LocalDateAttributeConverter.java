package com.dp.algolab_java_server.config;

import java.time.LocalDate;
import jakarta.persistence.Converter;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.AttributeConverter;
import java.time.format.DateTimeParseException;

@Converter(autoApply = true)
public class LocalDateAttributeConverter implements AttributeConverter<LocalDate, String> {
  private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE;
  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
  private static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

  @Override
  public String convertToDatabaseColumn(LocalDate localDate) {
    return localDate != null ? localDate.format(ISO_FORMATTER) : null;
  }

  @Override
  public LocalDate convertToEntityAttribute(String dbData) {
    if (dbData == null || dbData.isEmpty() || dbData.trim().isEmpty()) {
      return null;
    }

    String trimmed = dbData.trim();

    try {
      return LocalDate.parse(trimmed, ISO_FORMATTER);
    } catch (DateTimeParseException e) {
      try {
        return LocalDate.parse(trimmed, DATETIME_FORMATTER);
      } catch (DateTimeParseException e2) {
        try {
          return LocalDate.parse(trimmed, DATE_FORMATTER);
        } catch (DateTimeParseException e3) {
          if (trimmed.contains(" ")) {
            String datePart = trimmed.split(" ")[0];
            try {
              return LocalDate.parse(datePart, ISO_FORMATTER);
            } catch (DateTimeParseException e4) {
              throw new IllegalArgumentException("No se pudo parsear la fecha: " + dbData, e4);
            }
          }
          throw new IllegalArgumentException("No se pudo parsear la fecha: " + dbData, e3);
        }
      }
    }
  }
}
