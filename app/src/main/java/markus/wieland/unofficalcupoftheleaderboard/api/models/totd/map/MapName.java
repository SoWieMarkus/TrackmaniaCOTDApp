package markus.wieland.unofficalcupoftheleaderboard.api.models.totd.map;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;

import java.util.ArrayList;
import java.util.List;

public class MapName {

    private final SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
    private List<String> rules;

    private String currentColor = "#ffffff";
    private boolean bold = false;
    private boolean italic = false;
    private boolean upperCase = false;

    public MapName(String text) {
        extract(text);
    }

    private void extract(String text) {
        char[] chars = text.toCharArray();
        this.rules = new ArrayList<>();
        char[] stylingRules = new char[]{'w', 'n', 'o', 'i', 't', 's', 'g', 'z', '$', 'W', 'N', 'O', 'I', 'T', 'S', 'G', 'Z'};
        for (int i = 0; i < chars.length; i++) {
            char currentChar = chars[i];
            if (currentChar == '$') {
                char nextChar = chars[i + 1];
                boolean found = false;
                for (char stylingChar : stylingRules) {
                    if (nextChar == stylingChar) {
                        rules.add("$" + nextChar);
                        found = true;
                        break;
                    }
                }
                if (found) {
                    i++;
                } else {
                    String color = "$";

                    if (String.valueOf(chars[i+1]).matches("[0-9a-fA-F]")){
                        color += chars[i+1] +""+ chars[i+1];
                    } else {
                        rules.add("$000000");
                        continue;
                    }

                    if (String.valueOf(chars[i+2]).matches("[0-9a-fA-F]")){
                        color += chars[i+2] +""+ chars[i+2];
                    } else {
                        i++;
                        rules.add(color + "0000");
                        continue;
                    }

                    if (String.valueOf(chars[i+3]).matches("[0-9a-fA-F]")){
                        color += chars[i+3] +""+ chars[i+3];
                    } else {
                        i+=2;
                        rules.add(color + "00");
                        continue;
                    }

                    i = i + 3;
                    rules.add(color);
                }
            } else {
                rules.add(String.valueOf(currentChar));
            }
        }
    }

    public SpannableStringBuilder build() {
        for (String command : rules) {
            if (command.charAt(0) == '$') {
                if ("$o".equalsIgnoreCase(command.toLowerCase())) {
                    bold = true;
                } else if (command.toLowerCase().equalsIgnoreCase("$i")) {
                    italic = true;
                } else if (command.toLowerCase().equalsIgnoreCase("$t")) {
                    upperCase = true;
                } else if (command.toLowerCase().equalsIgnoreCase("$$")) {
                    append("$");
                } else if (command.toLowerCase().equalsIgnoreCase("$z")) {
                    italic = false;
                    bold = false;
                    upperCase = false;
                } else if (command.toLowerCase().length() == 7) {
                    currentColor = command.replace("$", "#");
                }
            } else {
                append(command);
            }
        }
        return spannableStringBuilder;
    }

    private void append(String text) {

        if (upperCase) text = text.toUpperCase();
        SpannableString string = new SpannableString(text);
        if (italic && bold)
            string.setSpan(new StyleSpan(Typeface.BOLD_ITALIC), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        else if (italic)
            string.setSpan(new StyleSpan(Typeface.ITALIC), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        else if (bold)
            string.setSpan(new StyleSpan(Typeface.BOLD), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        string.setSpan(new ForegroundColorSpan(Color.parseColor(currentColor)), 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append(string);

    }

}
