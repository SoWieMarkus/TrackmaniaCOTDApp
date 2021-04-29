package markus.wieland.trackmaniacotdapp.ui.about;

import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import markus.wieland.defaultappelements.uielements.fragments.DefaultFragment;
import markus.wieland.trackmaniacotdapp.R;

public class AboutFragment extends DefaultFragment {

    private TextView textViewCredits;
    private TextView textViewQuestions;

    public AboutFragment() {
        super(R.layout.fragment_about);
    }

    @Override
    public void bindViews() {
        textViewCredits = findViewById(R.id.fragment_about_credits);
        textViewQuestions = findViewById(R.id.fragment_about_questions);
    }

    @Override
    public void initializeViews() {
        textViewCredits.setMovementMethod(LinkMovementMethod.getInstance());
        textViewQuestions.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void execute() {
        // Nothing to do here
    }
}
