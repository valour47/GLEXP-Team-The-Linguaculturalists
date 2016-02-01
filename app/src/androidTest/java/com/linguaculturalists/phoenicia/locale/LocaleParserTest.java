package com.linguaculturalists.phoenicia.locale;

import android.test.AndroidTestCase;

import com.linguaculturalists.phoenicia.Locale;
import com.linguaculturalists.phoenicia.util.LocaleLoader;
import com.linguaculturalists.phoenicia.util.PhoeniciaContext;

import org.andengine.util.debug.Debug;

import java.io.IOException;

/**
 * Created by mhall on 1/31/16.
 */
public class LocaleParserTest extends AndroidTestCase {

    private String test_locale_manifest;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.test_locale_manifest = "locales/en_us_test/manifest.xml";
        PhoeniciaContext.context = getContext();
        PhoeniciaContext.assetManager = getContext().getAssets();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    private Locale getLocale() {
        LocaleLoader localeLoader = new LocaleLoader();
        Locale locale = null;
        try {
             locale = localeLoader.load(PhoeniciaContext.assetManager.open(this.test_locale_manifest));
        } catch (final IOException e) {
        }
        assertNotNull("Failed to load Locale", locale);
        return locale;
    }

    public void testLocaleDefinitions() {
        Locale locale = getLocale();
        assertEquals("en_us_test", locale.name);
        assertEquals("en_us", locale.lang);
        assertEquals("US English, Testing", locale.display_name);

        assertEquals("locales/en_us_test/textures/gameui.png", locale.shell_src);
        assertEquals("locales/en_us_test/textures/map.tmx", locale.map_src);
    }

    public void testLetterDefinitions() {
        Locale locale = getLocale();
        assertEquals(2, locale.letters.size());
        Letter a = locale.letter_map.get("a");
        assertNotNull(a);
        assertEquals("a", a.name);
        assertEquals(1, a.buy);
        assertEquals(1, a.sell);
        assertEquals(10, a.time);
        assertEquals(100, a.points);
        assertEquals("locales/en_us_test/textures/letters/a.png", a.texture_src);
        assertEquals("sounds/a.ogg", a.sound);
        assertEquals("phonemes/a.ogg", a.phoneme);
    }

    public void testWordDefinitions() {
        Locale locale = getLocale();
        assertEquals(2, locale.words.size());
        Word ab = locale.word_map.get("ab");
        assertNotNull(ab);
        assertEquals("ab", ab.name);
        assertEquals(1, ab.sell);
        assertEquals(10, ab.buy);
        assertEquals(10, ab.construct);
        assertEquals(10, ab.time);
        assertEquals(100, ab.points);
        assertEquals("locales/en_us_test/textures/words/ab.png", ab.texture_src);
        assertEquals("sounds/ab.ogg", ab.sound);
    }

    public void testLevelDefinitions() {
        Locale locale = getLocale();
        assertEquals(2, locale.levels.size());
        Level test1 = locale.level_map.get("test1");
        assertNotNull(test1);
        assertEquals("test1", test1.name);
    }

    public void testLevelIntro() {
        Locale locale = getLocale();
        Level test1 = locale.level_map.get("test1");
        assertNotNull(test1);
        assertEquals(1, test1.intro.size());
        IntroPage page1 = test1.intro.get(0);
        assertEquals("Test level 1 intro page", page1.text);
        assertEquals("sounds/intro1p1.ogg", page1.sound);
    }

    public void testLevelLetters() {
        Locale locale = getLocale();
        Level test1 = locale.level_map.get("test1");
        assertNotNull(test1);
        assertEquals(2, test1.letters.size());
        assertEquals("a", test1.letters.get(0).name);
        assertEquals("b", test1.letters.get(1).name);
    }

    public void testLevelWords() {
        Locale locale = getLocale();
        Level test1 = locale.level_map.get("test1");
        assertNotNull(test1);
        assertEquals(0, test1.words.size());

        Level test2 = locale.level_map.get("test2");
        assertNotNull(test2);
        assertEquals(1, test2.words.size());
        assertEquals("ab", test2.words.get(0).name);
    }

    public void testLevelHelp() {
        Locale locale = getLocale();
        Level test1 = locale.level_map.get("test1");
        assertNotNull(test1);
        assertEquals("a", test1.help_letters.get(0).name);
        assertEquals(1, test1.help_letters.size());
        assertEquals(0, test1.help_words.size());
        assertEquals("a", test1.help_letters.get(0).name);

        Level test2 = locale.level_map.get("test2");
        assertNotNull(test2);
        assertEquals(2, test2.help_letters.size());
        assertEquals(1, test2.help_words.size());
        assertEquals("a", test2.help_letters.get(0).name);
        assertEquals("b", test2.help_letters.get(1).name);
        assertEquals("ab", test2.help_words.get(0).name);
    }
}