package com.br.curso.rockpaperscissors;

public enum GameChoicesEnum {
    ROCK(0, "Rock"),
    PAPER(1, "Paper"),
    SCISSORS(2, "Scissors");

    private Integer id;
    private String description;

    GameChoicesEnum(Integer id, String description) {
        this.id = id;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
