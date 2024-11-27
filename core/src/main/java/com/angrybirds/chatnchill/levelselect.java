package com.angrybirds.chatnchill;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class levelselect implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture bg;
    private Texture selectlevel;
    private Texture L1;
    private Texture L2;
    private Texture L3;
    private Texture L4;
    private Texture backbutton;
    private Texture settings;
    private FitViewport viewport;
    private Sprite sprite;
    private Sound click;

    public levelselect(Main game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        bg = new Texture("levelmenu.jpg");
        bg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        settings = new Texture("settings.png");
        settings.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        selectlevel = new Texture("select_level_1.png");
        selectlevel.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        L1 = new Texture("L1.png");
        L2 = new Texture("L2.png");
        L3 = new Texture("L3.png");
        L4 = new Texture("L4.png");
        backbutton = new Texture("back.png");
        viewport = new FitViewport(10.1f,5.2f);
        sprite = new Sprite(bg);
        click = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height,true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void render(float delta) {
        draw();

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            // Check if the user clicked the level 1
            float l1x = 2.9f;
            float l1y = 2.7f;
            float l1width = 1.6f;
            float l1height = 0.7f;

            if (touchPos.x >= l1x && touchPos.x <= l1x + l1width &&
                touchPos.y >= l1y && touchPos.y <= l1y + l1height) {
                click.play();
                game.setScreen(new level1(game));
            }

            //check if the user clicked the level2
            float l2x = 5.4f;
            float l2y = 2.66f;
            float l2width = 1.58f;
            float l2height = 0.76f;

            if (touchPos.x>=l2x && touchPos.x<=l2x+l2width &&
                touchPos.y>=l2y && touchPos.y<=l2y+l2height){
                click.play();
                game.setScreen(new level2(game));
            }

            //check if the user clicked the level3
            float l3x = 2.9f;
            float l3y = 1.6f;
            float l3width = 1.7f;
            float l3height = 0.76f;
            if (touchPos.x>=l3x && touchPos.x<=l3x+l3width &&
                touchPos.y>=l3y && touchPos.y<=l3y+l3height){
                click.play();
                game.setScreen(new level3(game));
            }


            //check if user clicked the back button
            float backx = 0.2f;
            float backy = 4.3f;
            float backwidht = 0.5f;
            float backheight = 0.5f;
            if(touchPos.x>=backx && touchPos.x<=backx+backwidht &&
                touchPos.y>=backy && touchPos.y<=backy+backheight){
                click.play();
                game.setScreen(new Mainscreen(game));
            }

        }

    }

    private void draw(){
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldWidth();
        batch.draw(bg,0,0,worldWidth,worldHeight);

        //select level text
        float textx = 2.4f;
        float texty = 3.6f;
        float textwidth = 5.0f;
        float textheight = 1.0f;
        batch.draw(selectlevel,textx,texty,textwidth,textheight);

        //level1 button
        float l1x = 2.9f;
        float l1y = 2.7f;
        float l1width = 1.6f;
        float l1height = 0.7f;
        batch.draw(L1,l1x,l1y,l1width,l1height);

        //level2 button
        float l2x = 5.4f;
        float l2y = 2.66f;
        float l2width = 1.58f;
        float l2height = 0.76f;
        batch.draw(L2,l2x,l2y,l2width,l2height);

        //level3 button
        float l3x = 2.9f;
        float l3y = 1.6f;
        float l3width = 1.7f;
        float l3height = 0.76f;
        batch.draw(L3,l3x,l3y,l3width,l3height);

        //level4 button
        float l4x = 5.38f;
        float l4y = 1.64f;
        float l4width = 1.7f;
        float l4height = 0.73f;
        batch.draw(L4,l4x,l4y,l4width,l4height);

//        settings button
//        float settingsx = 9.3f;
//        float settingsy = 4.5f;
//        float settingswidht = 0.5f;
//        float settingsheight = 0.5f;
//        batch.draw(settings,settingsx,settingsy,settingswidht,settingsheight);

        //back button
        float backx = 0.2f;
        float backy = 4.5f;
        float backwidht = 0.5f;
        float backheight = 0.5f;
        batch.draw(backbutton,backx,backy,backwidht,backheight);

        batch.end();
    }

    public void hide(){
        dispose();
    }
    @Override
    public void dispose() {
        batch.dispose();
        bg.dispose();
    }
}
