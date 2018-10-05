package com.github.aistomin.guitarist.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

/**
 * Created by aistomin on 05.10.18.
 * <p>
 * Test for {@link MusicSymbol}
 */
class MusicSymbolTest {

    /**
     * Check that we correctly get the symbol string.
     */
    @Test
    void symbol() {
        assertEquals("♩", MusicSymbol.QUARTER_NOTE.symbol());
    }

    /**
     * Check that we correctly get the description of the symbol.
     */
    @Test
    void description() {
        assertEquals("Crescendo", MusicSymbol.CRESCENDO.description());
    }

    /**
     * Check that we correctly convert the entity to JSON string.
     *
     * @throws ParseException If JSON paring exception occurred.
     */
    @Test
    void toJsonString() throws ParseException {
        final JSONObject json = (JSONObject) new JSONParser()
            .parse(MusicSymbol.FERMATA.toJsonString());
        assertEquals("FERMATA", json.get("name"));
        assertEquals("Fermata", json.get("description"));
        assertEquals("\uD834\uDD10", json.get("symbol"));
    }

    /**
     * Check that we correctly display the symbol.
     */
    @Test
    void toDisplayableString() {
        assertEquals(
            "Breath Mark: \uD834\uDD12",
            MusicSymbol.BREATH.toDisplayableString()
        );
    }

    /**
     * Check that we can find similar symbols.
     */
    @Test
    void similar() {
        final MusicSymbol symbol = MusicSymbol.random();
        final List<MusicSymbol> similar = symbol.similar();
        assertTrue(similar.size() > 0);
        for (final MusicSymbol item : similar) {
            assertEquals(symbol.type(), item.type());
        }
    }
}
