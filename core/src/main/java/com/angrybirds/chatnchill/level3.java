package com.angrybirds.chatnchill;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;

import java.util.ArrayList;

public class level3 implements Screen {
    private Main game;
    private ShapeRenderer shapeRenderer;
    private SpriteBatch batch;
    private Texture cloudybg;
    private Texture slingshot;
    private bird redbird;
    private bird bluebird;
    private bird yellowbird;
    private Texture savebutton;
    private pig pig1;
    private pig pig2;
    private pig pig3;
    private pig pig4;
    private block glassbox;
    private block woodenbox;
    private block steelbox;
    private Texture pausebutton;
    private Sprite sprite;
    private FitViewport viewport;
    private boolean isdragging = false;
    private Vector2 dragStartPosition = new Vector2();
    private Vector2 dragEndPosition = new Vector2();
    private boolean isTrajectoryVisible = false;
    private Vector2 birdVelocity = new Vector2();
    private Vector2 birdPosition = new Vector2();
    private boolean isBirdReleased = false;
    private float gravity = -9.8f;
    private ArrayList<block> blocks;
    private ArrayList<pig> pigs;
    private ArrayList<bird> birds;
    private Sound killsound;
    private Sound losesound;
    private Sound winsound;
    private Sound click;
    private Sound stretch;
    private Sound release;
    private Sound woodhit;
    private Vector2 slingshotposition = new Vector2(0.9f,1.8f);

    public level3(Main game){
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        cloudybg = new Texture("cloudy.png");
        slingshot = new Texture("slingshot.png");
        pausebutton = new Texture("pause.png");
        savebutton = new Texture("savebutton.png");

        viewport = new FitViewport(10.1f,5.2f);
        sprite = new Sprite(cloudybg);
        shapeRenderer = new ShapeRenderer();

        redbird = new redbird(0.46f,1.65f,1.2f,0.6f);
        bluebird = new bluebird(0.1f,1.05f,0.55f,0.55f);
        yellowbird = new yellowbird(0.5f,1.05f,0.5f,0.5f);
        birds = new ArrayList<>();
        birds.add(redbird);
        birds.add(yellowbird);
        birds.add(bluebird);

        pig1 = new pig1(5.46f,2.9f,0.6f,0.5f);
        pig2 = new pig2(6.17f,2.86f,0.44f,0.5f);
        pig3 = new pig3(8.3f,2.1f,0.6f,0.5f);
        pigs = new ArrayList<>();
        pigs.add(pig1);
        pigs.add(pig2);
        pigs.add(pig3);


        woodenbox = new woodenbox(10.5f,1.15f,0.3f,0.3f,1);
        glassbox = new glassbox(10.9f,1.33f,0.5f,0.5f,2);
        steelbox = new steelbox(10.4f,1.023f,0.5f,0.5f,3);
        blocks = new ArrayList<>();
        blocks.add(woodenbox);
        blocks.add(glassbox);
        blocks.add(steelbox);
        createtower();

        killsound = Gdx.audio.newSound(Gdx.files.internal("killsound.mp3"));
        losesound = Gdx.audio.newSound(Gdx.files.internal("losesound.mp3"));
        winsound = Gdx.audio.newSound(Gdx.files.internal("winsound.mp3"));
        click = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
        stretch = Gdx.audio.newSound(Gdx.files.internal("stretch.mp3"));
        release = Gdx.audio.newSound(Gdx.files.internal("release.mp3"));
        woodhit = Gdx.audio.newSound(Gdx.files.internal("woodenhit.mp3"));



    }


    public void savegame(){
        gamestate currentstate = new gamestate(
            new ArrayList<>(birds),
            new ArrayList<>(pigs),
            new ArrayList<>(blocks),
            isBirdReleased,
            new Vector2(birdPosition),
            new Vector2(birdVelocity),
            "level3"
        );

        gamestatemanager.savegame(currentstate);
    }

    @Override
    public void render(float delta) {
        draw();

        handleinput();

        if (Gdx.input.justTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            // Check if the user clicked the pause icon
            float pausex = 0.15f;
            float pausey = 4.66f;
            float pausewidht = 0.4f;
            float pauseheight = 0.4f;

            if (touchPos.x >= pausex && touchPos.x <= pausex + pausewidht &&
                touchPos.y >= pausey && touchPos.y <= pausey + pauseheight) {
                click.play();
                game.setScreen(new pausemenu(game,this));
            }

        }

        if(isBirdReleased){
            updateBirdPosition(delta);
        }

        checkgamecondition();

        // Draw trajectory if dragging
        if (isTrajectoryVisible) {
            drawTrajectory();
        }
    }

    private void checkgamecondition(){
        if(birds.isEmpty() && !pigs.isEmpty()){
            losesound.play();
            game.setScreen(new losescreen(game));
        }
        else if(!birds.isEmpty() && pigs.isEmpty()){
            winsound.play();
            game.setScreen(new winscreen(game));
        }
        else if(birds.isEmpty() && pigs.isEmpty()){
            winsound.play();
            game.setScreen(new winscreen(game));
        }
    }

    private void handleinput(){
        if (Gdx.input.isTouched()) {
            Vector2 touchPos = new Vector2(Gdx.input.getX(), Gdx.input.getY());
            viewport.unproject(touchPos);

            if (!isdragging && !birds.isEmpty()) {
                bird currentbird = birds.get(0);
                float birdX = currentbird.x;
                float birdY = currentbird.y;
                float birdWidth = currentbird.width;
                float birdHeight = currentbird.height;

                if (touchPos.x >= birdX && touchPos.x <= birdX + birdWidth &&
                    touchPos.y >= birdY && touchPos.y <= birdY + birdHeight) {
                    isdragging = true;
                    dragStartPosition.set(touchPos);
                    isTrajectoryVisible = true;
                    stretch.play();
                }
            } else if(isdragging) {

                dragEndPosition.set(touchPos);
            }
        } else if (isdragging) {
            // Bird released
            isdragging = false;
            isTrajectoryVisible = false;
            isBirdReleased = true;

            birdVelocity.set(dragStartPosition).sub(dragEndPosition).scl(6);
            if(!birds.isEmpty()){
                bird currentbird = birds.get(0);
                birdPosition.set(currentbird.x, currentbird.y);
                release.play();
            }

        }

    }

    private void drawTrajectory() {
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.BLACK);

        if(!birds.isEmpty()){
            Vector2 simulatedPosition = new Vector2(slingshotposition);
            Vector2 simulatedVelocity = new Vector2(dragStartPosition).sub(dragEndPosition).scl(7);
            float gravity = -9.8f;
            float gapbetweendots = 0.05f;
            int dots = 30;

            for (int i = 0; i < dots; i++) {
                float t = i * gapbetweendots;
                float nextX = simulatedPosition.x + simulatedVelocity.x * t;
                float nextY = simulatedPosition.y + simulatedVelocity.y * t + 0.5f * gravity * t * t;

                shapeRenderer.circle(nextX,nextY,0.05f);
            }

        }

        shapeRenderer.end();
    }

    private void updateBirdPosition(float delta) {
        if(birds.isEmpty()) return;
        bird currentbird = birds.get(0);
        birdVelocity.y += gravity * delta;
        birdPosition.x += (birdVelocity.x+0.6f) * delta;
        birdPosition.y += birdVelocity.y * delta;


        if (birdPosition.y <= 1.05f) {
            birdPosition.y = 1.05f;
            birdVelocity.y *= -0.6f;
            currentbird.bouncecount--;

            if(currentbird.bouncecount<=0) {
                birds.remove(currentbird);
                handleNextBird();
                return;
            }
        }


        // Check for collisions with blocks
        for (int i = 0; i < blocks.size(); i++) {
            block currentBlock = blocks.get(i);
            if (collisionwithbox(currentbird, currentBlock)) {
                birdVelocity.x *= -0.8f;
                birdVelocity.y *= 0.8f;
                woodhit.play();
                blocks.remove(i);
                currentbird.bouncecount--;

                if(currentbird.bouncecount<=0) {
                    birds.remove(currentbird);
                    handleNextBird();
                    return;
                }
            }
        }

        // Check for collisions with pigs
        for (int i = 0; i < pigs.size(); i++) {
            pig currentPig = pigs.get(i);
            if (collisionwithpig(currentbird, currentPig)) {
                birdVelocity.x *= -0.8f;
                birdVelocity.y *= 0.8f;
                killsound.play();
                pigs.remove(currentPig);
                currentbird.bouncecount--;

                if(currentbird.bouncecount<=0) {
                    birds.remove(currentbird);
                    handleNextBird();
                    return;
                }
            }
        }

        currentbird.x = birdPosition.x;
        currentbird.y = birdPosition.y;
    }

    private void handleNextBird() {
        isBirdReleased = false;
        isTrajectoryVisible = false;
        isdragging = false;
        if (!birds.isEmpty()) {
            bird nextBird = birds.get(0);
            nextBird.x = 0.8f;
            nextBird.y = 1.6f;
            birdPosition.set(nextBird.x, nextBird.y);
            dragStartPosition.set(nextBird.x,nextBird.y);
        }
    }

    private boolean collisionwithbox(bird bird, block block){
        return bird.x < block.x + block.width &&
            bird.x + bird.width > block.x &&
            bird.y < block.y + block.height &&
            bird.y + bird.height > block.y;
    }

    private boolean collisionwithpig(bird bird, pig pig){
        return bird.x < pig.x + pig.width &&
            bird.x + bird.width > pig.x &&
            bird.y < pig.y + pig.height &&
            bird.y + bird.height > pig.y;
    }

    private void draw(){
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        batch.draw(cloudybg,0,0,worldWidth,worldHeight);

        //slingshot dimension and position
        float slingshotx = 0.5f;
        float slingshoty = 1.05f;
        float slingwidht = 1;
        float slingheight = 0.8f;
        batch.draw(slingshot,slingshotx,slingshoty,slingwidht,slingheight);

        for(bird currentbird : birds ){
            currentbird.draw(batch);
        }

        // Draw blocks
        for (block currentBlock : blocks) {
            currentBlock.draw(batch);
        }

        // Draw pigs
        for (pig currentPig : pigs) {
            currentPig.draw(batch);
        }

        float pausex = 0.15f;
        float pausey = 4.66f;
        float pausewidht = 0.4f;
        float pauseheight = 0.4f;
        batch.draw(pausebutton,pausex,pausey,pausewidht,pauseheight);

        //save button
        float savex = 9.4f;
        float savey = 4.6f;
        float savewidht = 0.5f;
        float saveheight = 0.5f;
        batch.draw(savebutton,savex,savey,savewidht,saveheight);

        batch.end();
    }

    private void createtower(){
        int woodtowerid = 1;
        int glasstowerid = 2;
        int steeltowerid = 3;
        //woodenbox dimensions and position
        float woodenboxx = 5.5f;
        float woodenboxy = 1.15f;
        float boxwidht = 0.45f;
        float boxheigt = 0.45f;

        //glassbox dimensions and position
        float glassboxx = 6.4f;
        float glassboxy = 1.42f;
        float glassboxwidht = 0.8f;
        float glassboxheight = 0.8f;

        //steelbox dimensions and position
        float steelboxx = 7.4f;
        float steelboxy = 0.93f;
        float steelboxwidht = 0.8f;
        float steelboxheight = 0.8f;

        //vertical glassbox
        float overlap = 0.37f;
        for(int i = 0;i<3;i++){
            blocks.add(new glassbox(5.947f,1.46f+i*(0.8f-overlap),0.8f,0.8f,glasstowerid));
        }

        //vertical woodenbox
        for(int i = 0;i<4;i++){
            blocks.add(new woodenbox(woodenboxx,woodenboxy+i*boxheigt,boxwidht,boxheigt,woodtowerid));
        }

        //horizontal woodenbox
        for(int i = 0;i<6;i++){
            blocks.add(new woodenbox(woodenboxx+i*boxwidht,woodenboxy,boxwidht,boxheigt,woodtowerid));
        }

        //horizontal glassbox
        float gap = 0.35f;
        for(int i = 0;i<4;i++){
            blocks.add(new glassbox(glassboxx+ i* (glassboxwidht-gap),glassboxy+(boxheigt*0.09f),glassboxwidht,glassboxheight,glasstowerid));
        }

        int no_of_box = 2;
        int rows = 2;
        for(int i = 0;i<rows;i++){
            for(int j = 0;j<no_of_box;j++){
                blocks.add(new steelbox(steelboxx*1.05f+(steelboxwidht*0.58f) + (j*steelboxwidht*0.58f),steelboxy+(steelboxheight*0.09f)+(i*steelboxheight*0.58f),steelboxwidht,steelboxheight,steeltowerid));
            }
        }
    }

    public void resize(int width,int height){

        viewport.update(width,height,true);
    }

    public void pause() {
    }

    @Override
    public void resume() {
    }

    public void hide(){
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        cloudybg.dispose();
        slingshot.dispose();
        pausebutton.dispose();
        killsound.dispose();
        woodhit.dispose();
    }

}
