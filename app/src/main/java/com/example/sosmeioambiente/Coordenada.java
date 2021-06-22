package com.example.sosmeioambiente;

public class Coordenada {
    private String latitude, longitudade;

    public Coordenada() {
    }

    public Coordenada(String latitude, String longitudade) {
        this.latitude = latitude;
        this.longitudade = longitudade;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitudade() {
        return longitudade;
    }

    public void setLongitudade(String longitudade) {
        this.longitudade = longitudade;
    }

    @Override
    public String toString() {
        return "Coordenada{" +
                "latitude='" + latitude + '\'' +
                ", longitudade='" + longitudade + '\'' +
                '}';
    }
}
