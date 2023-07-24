package com.example.quanlyrapphim.models;

public class ShowTime {
    private TimeSlot timeSlot;
    private Film film;
    private CinemaRoom cinemaRoom;

    public ShowTime(Film film, CinemaRoom cinemaRoom, TimeSlot timeSlot) {
        this.film = film;
        this.cinemaRoom = cinemaRoom;
        this.timeSlot = timeSlot;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public CinemaRoom getCinemaRoom() {
        return cinemaRoom;
    }

    public void setCinemaRoom(CinemaRoom cinemaRoom) {
        this.cinemaRoom = cinemaRoom;
    }
}
