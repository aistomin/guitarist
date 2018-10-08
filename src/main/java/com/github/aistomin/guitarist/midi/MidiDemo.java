package com.github.aistomin.guitarist.midi;

import javax.sound.midi.MidiUnavailableException;

/**
 * Created by aistomin on 08.10.18.
 * <p>
 * Demo class to play MIDI.
 */
public final class MidiDemo {

    /**
     * Runnable method.
     *
     * @param args Arguments.
     */
    public static void main(final String[] args) {
        try {
            final MidiPlayer player = new MidiPlayer();
            final int velocity = 100;
            player.play(60, velocity);
            player.play(62, velocity);
            player.play(64, velocity);
            player.play(65, velocity);
            player.play(67, velocity);
            player.play(69, velocity);
            player.play(71, velocity);
        } catch (final MidiUnavailableException ex) {
            System.out.println("MIDI output is not available on your computer: ");
            ex.printStackTrace();
        }
    }
}
