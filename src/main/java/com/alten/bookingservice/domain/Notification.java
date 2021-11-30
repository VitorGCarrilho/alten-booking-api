package com.alten.bookingservice.domain;

import java.io.Serializable;

public class Notification implements Serializable {
    private Booking booking;
    private NotificationType notificationType;

    public Notification(){}

    public Notification(Booking booking, NotificationType notificationType) {
        this.booking = booking;
        this.notificationType = notificationType;
    }

    public Booking getBooking() {
        return booking;
    }

    public NotificationType getNotificationType() {
        return notificationType;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "booking=" + booking +
                ", notificationType=" + notificationType +
                '}';
    }

    public enum NotificationType {
        BOOKED("email/booked", "Stay Booked!"),
        ROOM_ALREADY_BOOKED("email/already-booked", "Date not available!"),
        UPDATE_BOOKED("email/update-booked", "Book Changed!"),
        UPDATE_ROOM_ALREADY_BOOKED("email/update-already-booked", "Date not available!"),
        BOOK_CANCELLED("email/cancel", "Cancel confirmation");

        private final String template;
        private final String subject;

        NotificationType(String template, String subject) {
            this.template = template;
            this.subject = subject;
        }

        public String getTemplate() {
            return template;
        }

        public String getSubject() {
            return subject;
        }
    }
}
