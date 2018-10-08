package com.github.aistomin.guitarist.midi;

import javax.sound.midi.Instrument;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

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
            Synthesizer synth = MidiSystem.getSynthesizer();
            synth.open();
            Instrument[] instr = synth.getDefaultSoundbank().getInstruments();
            MidiChannel[] mChannels = synth.getChannels();
            synth.loadInstrument(instr[0]);
            mChannels[0].noteOn(60, 100);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            mChannels[0].noteOff(60);//turn of the note
        } catch (MidiUnavailableException e) {
        }
    }
}
