package com.ktk.orca.core.utilities;

import org.springframework.stereotype.Component;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;
import org.threeten.bp.temporal.ChronoUnit;


@Component
public class DateUtils {

    private DateUtils() {
    }

    /**
     * Create a zoned LocalDate object using now as start and deducting the offSet to get days-ago.
     *
     * @param offSet
     * @return LocalDate
     */
    public static LocalDate getZonedDate(int offSet) {

        LocalDate startDate = LocalDate.now().minus(offSet, ChronoUnit.DAYS);
        return (ZonedDateTime.of(startDate.atStartOfDay(), ZoneId.of("UTC"))).toLocalDate();
    }

    /**
     * Create a zoned LocalDateTime object using now as start and deducting the offSet to get days-ago.
     *
     * @param offSet
     * @return
     */
    public static LocalDateTime getZonedDateTime(int offSet) {

        LocalDate startDate = LocalDate.now().minus(offSet, ChronoUnit.DAYS);
        return (ZonedDateTime.of(startDate.atStartOfDay(), ZoneId.of("UTC"))).toLocalDateTime();
    }
}
