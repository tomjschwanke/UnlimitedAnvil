package de.tomjschwanke.unlimitedanvil;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnvilEventsTest {

    @DisplayName("Convert number to upside down string")
    @org.junit.jupiter.api.Test
    void upsideDownNumber() {
        AnvilEvents anvilEvents = new AnvilEvents();
        String result = anvilEvents.upsideDownNumber(1372450869);
        assertEquals("6980ϛᔭᄅㄥƐƖ", result);
    }

    @DisplayName("Cost message localized to English")
    @Test
    void localizeEnUs() {
        AnvilEvents anvilEvents = new AnvilEvents();
        String[] result = anvilEvents.localizedCost("en_us");
        String[] desired = {"Cost: ", " levels", null};
        assertArrayEquals(desired, result);
    }

    @DisplayName("Cost message localized to German")
    @Test
    void localizeDeDe() {
        AnvilEvents anvilEvents = new AnvilEvents();
        String[] result = anvilEvents.localizedCost("de_de");
        String[] desired = {"Kosten: ", " Level", null};
        assertArrayEquals(desired, result);
    }

    @DisplayName("Cost message localized to upside down English")
    @Test
    void localizeEnUd() {
        AnvilEvents anvilEvents = new AnvilEvents();
        String[] result = anvilEvents.localizedCost("en_ud");
        String[] desired = {"slǝʌǝ˥ ", " :ʇsoƆ", "inv"};
        assertArrayEquals(desired, result);
    }

    @DisplayName("Cost message localized to Spanish, Mexico")
    @Test
    void localizeEsMx() {
        AnvilEvents anvilEvents = new AnvilEvents();
        String[] result = anvilEvents.localizedCost("es_mx");
        String[] desired = {"Costo: ", " niveles", null};
        assertArrayEquals(desired, result);
    }

    @DisplayName("Cost message fallback to English")
    @Test
    void localizeUnknown() {
        AnvilEvents anvilEvents = new AnvilEvents();
        String[] result = anvilEvents.localizedCost("aerhatrfdA$3");
        String[] desired = {"Cost: ", " levels", null};
        assertArrayEquals(desired, result);
    }
}