package tn.esprit.spring.entities;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PisteDTO {
    private Long numPiste;

    @NotBlank
    private String namePiste;

    @NotNull
    private Color color;

    @NotNull
    private int length;

    @NotNull
    private int slope;

    // Constructor, getters, and setters

    public PisteDTO(Long numPiste, String namePiste, Color color, int length, int slope) {
        this.numPiste = numPiste;
        this.namePiste = namePiste;
        this.color = color;
        this.length = length;
        this.slope = slope;
    }

    // Getters and setters

    public Long getNumPiste() {
        return numPiste;
    }

    public void setNumPiste(Long numPiste) {
        this.numPiste = numPiste;
    }

    public String getNamePiste() {
        return namePiste;
    }

    public void setNamePiste(String namePiste) {
        this.namePiste = namePiste;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getSlope() {
        return slope;
    }

    public void setSlope(int slope) {
        this.slope = slope;
    }
}