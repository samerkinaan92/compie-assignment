package com.example.compieassignment.dto;

import java.util.List;

public class PlayersDto {
    private List<Player> data;
    private Meta meta;

    public List<Player> getData() {
        return data;
    }

    public void setData(List<Player> data) {
        this.data = data;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
