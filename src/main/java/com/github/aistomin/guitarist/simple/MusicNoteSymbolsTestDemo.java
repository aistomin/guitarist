package com.github.aistomin.guitarist.simple;

import com.github.aistomin.guitarist.domain.MusicSymbols;

/**
 * Created by aistomin on 04.10.18.
 */
public class MusicNoteSymbolsTestDemo {
    public static void main(String[] args) {
        for (MusicSymbols item : MusicSymbols.values()) {
            System.out.println(item.toDisplayableString());
        }
    }
}
