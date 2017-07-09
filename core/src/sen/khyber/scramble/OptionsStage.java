package sen.khyber.scramble;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;

/**
 * 
 * 
 * @author Khyber Sen
 */
public class OptionsStage extends FitStage {
    
    private final Label scrambleLabel;
    private final IntField boardSizeInput;
    private final TextButton playButton;
    
    public OptionsStage(final Batch batch, final StageScreen<? extends OptionsStage> screen) {
        super(batch, screen);
        final Skin skin = Skins.get();
        
        final float width = getWidth();
        final float height = getHeight();
        
        scrambleLabel = new Label("Scramble", skin.get("labelStyle", LabelStyle.class));
        scrambleLabel.setBounds(width * .4f, height * .7f, width * .9f, height * .1f);
        addActor(scrambleLabel);
        
        boardSizeInput = new IntField("Board Size",
                skin.get("textFieldStyle", TextFieldStyle.class));
        boardSizeInput.setBounds(width * .4f, height * .5f, width * .2f, height * .1f);
        addActor(boardSizeInput);
        
        playButton = new TextButton("Play",
                skin.get("textButtonStyle", TextButtonStyle.class));
        playButton.setBounds(width * .4f, height * .3f, width * .2f, height * .1f);
        addActor(playButton);
        
        System.out.println(scrambleLabel);
        System.out.println(boardSizeInput);
        System.out.println(playButton);
    }
    
    @Override
    public boolean isFinished() {
        return playButton.isPressed();
    }
    
    public int getBoardSize() {
        return boardSizeInput.getInt();
    }
    
    @Override
    public void draw() {
        if (screen.getFrameNum() == 0) {
            System.out.println("drawing label");
            batch.begin();
            scrambleLabel.draw(batch, 1);
            batch.end();
        }
        super.draw();
    }
    
}
