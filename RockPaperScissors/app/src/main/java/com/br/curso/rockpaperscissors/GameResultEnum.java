package com.br.curso.rockpaperscissors;

public enum GameResultEnum {
    WIN(0, "You Won!"),
    LOST(1, "You Lost!"),
    DRAW(2, "Draw");

    private Integer id;
    private String description;

    GameResultEnum(Integer id, String description) {
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
