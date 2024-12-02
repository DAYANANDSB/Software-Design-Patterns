import java.util.HashMap;
import java.util.Map;

// Creating an interface for translation
interface Translator {
    String translate(String text);
}

// Spanish translator
class SpanishTranslator implements Translator {
    private static final Map<String, String> translations = new HashMap<>();

    static {
        translations.put("Hola", "Hello");
        translations.put("Gracias", "Thank you");
    }

    @Override
    public String translate(String text) {
        
        return translations.getOrDefault(text, "Translation not found") + " (translated from Spanish)";    // Returns translation or default message
    }
}

// French translator 
class FrenchTranslator implements Translator {
    private static final Map<String, String> translations = new HashMap<>();

    // Static block to add translations
    static {
        translations.put("Bonjour", "Hello");
        translations.put("Merci", "Thank you");
    }

    @Override
    public String translate(String text) {
        return translations.getOrDefault(text, "Translation not found") + " (translated from French)";
    }
}

// Adapter class to handle different translators
class LanguageTranslatorAdapter implements Translator {
    private Map<String, Translator> translatorMap;
    private Translator translator;

    public LanguageTranslatorAdapter(String language) {
        // Initializing translator map
        translatorMap = new HashMap<>();
        translatorMap.put("Spanish", new SpanishTranslator());
        translatorMap.put("French", new FrenchTranslator());

        this.translator = translatorMap.get(language);
    }

    @Override
    public String translate(String text) {
        if (translator != null) {
            
            return translator.translate(text);
        } else {
            return "Language not supported";
        }
    }
}

public class LanguageTranslationAdapter{
    public static void main(String[] args) {
        // Traslation from Spanish and French
        Translator spanishAdapter = new LanguageTranslatorAdapter("Spanish");
        Translator frenchAdapter = new LanguageTranslatorAdapter("French");

        System.out.println("Spanish to English:");
        System.out.println(spanishAdapter.translate("Hola"));  
        System.out.println(spanishAdapter.translate("Adiós")); 
        // Translation from French to English
        System.out.println("\nFrench to English:");
        System.out.println(frenchAdapter.translate("Bonjour")); 
        System.out.println(frenchAdapter.translate("Au revoir")); 

        // Testing for unsupported language
        Translator unsupportedAdapter = new LanguageTranslatorAdapter("German");
        System.out.println("\nGerman to English:");
        System.out.println(unsupportedAdapter.translate("Hallo")); 
    }
}
