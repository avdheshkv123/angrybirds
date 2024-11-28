package com.angrybirds.chatnchill;

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
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class level1 implements Screen {
    private Main game;
    private SpriteBatch batch;
    private Texture nightbg;
    private Texture slingshot;
    private bird redbird;
    private bird bluebird;
    private bird yellowbird;
    private pig pig1;
    private pig pig2;
    private block glassbox;
    private block woodenbox;
    private Texture pausebutton;
    private Texture savebutton;
    private FitViewport viewport;
    private Sprite sprite;
    private ShapeRenderer shapeRenderer;
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
    private int launchbirdcount = 0;
    private Vector2 slingshotposition = new Vector2(0.9f,1.1f);


    public level1(Main game) {
        this.game = game;
    }


    @Override
    public void show() {
        batch = new SpriteBatch();
        nightbg = new Texture("night.png");
        nightbg.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        slingshot = new Texture("slingshot.png");
        pausebutton = new Texture("pause.png");
        savebutton = new Texture("savebutton.png");

        sprite = new Sprite(nightbg);
        shapeRenderer = new ShapeRenderer();
        viewport = new FitViewport(10.1f, 5.2f);

        redbird = new redbird(0.44f,1.05f,1.2f,0.6f);
        bluebird = new bluebird(0.05f,0.42f,0.6f,0.6f);
        yellowbird = new yellowbird(0.47f,0.42f,0.5f,0.6f);
        birds = new ArrayList<>();
        birds.add(redbird);
        birds.add(yellowbird);
        birds.add(bluebird);

        pig1 = new pig1(7.4f,1.93f,0.7f,0.6f);
        pig2 = new pig2(6.5f,0.94f,0.5f,0.5f);
        pigs = new ArrayList<>();
        pigs.add(pig1);
        pigs.add(pig2);

        woodenbox = new woodenbox(7,1,0.5f,0.5f,1);
        glassbox = new glassbox(17.9f,0.41f,0.5f,0.5f,2);
        blocks = new ArrayList<>();
        blocks.add(woodenbox);
        blocks.add(glassbox);
        createTower();

        killsound = Gdx.audio.newSound(Gdx.files.internal("killsound.mp3"));
        losesound = Gdx.audio.newSound(Gdx.files.internal("losesound.mp3"));
        winsound = Gdx.audio.newSound(Gdx.files.internal("winsound.mp3"));
        click = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
        stretch = Gdx.audio.newSound(Gdx.files.internal("stretch.mp3"));
        release = Gdx.audio.newSound(Gdx.files.internal("release.mp3"));
        woodhit = Gdx.audio.newSound(Gdx.files.internal("woodenhit.mp3"));
        launchbirdcount = 0;

    }


    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {

    }

    public void savegame(){
        gamestate currentstate = new gamestate(
            new ArrayList<>(birds),
            new ArrayList<>(pigs),
            new ArrayList<>(blocks),
            isBirdReleased,
            new Vector2(birdPosition),
            new Vector2(birdVelocity),
            "level1"
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
            float pausex = 0.13f;
            float pausey = 4.6f;
            float pausewidht = 0.4f;
            float pauseheight = 0.4f;

            if (touchPos.x >= pausex && touchPos.x <= pausex + pausewidht &&
                touchPos.y >= pausey && touchPos.y <= pausey + pauseheight) {
                click.play();
                game.setScreen(new pausemenu(game,this));
            }

            // Check if the user clicked the save icon
            float savex = 9.4f;
            float savey = 4.6f;
            float savewidht = 0.5f;
            float saveheight = 0.5f;

            if (touchPos.x >= savex && touchPos.x <= savex + savewidht &&
                touchPos.y >= savey && touchPos.y <= savey + saveheight) {
                click.play();
                savegame();
            }

        }


        if(isBirdReleased){
            updateBirdPosition(delta);
        }

        checkgamecondition();
        checkPigSupport(delta);
        // Draw trajectory if dragging
        if (isTrajectoryVisible) {
            drawTrajectory();
        }

    }


    private void checkgamecondition() {
        if (birds.isEmpty() && !pigs.isEmpty()) {
            losesound.play(0.3f);
            game.setScreen(new losescreen(game));
            return;
        }

        // Check if all pigs are cleared
        if (pigs.isEmpty()) {
            winsound.play();

            if (launchbirdcount == 1 && birds.isEmpty()) {
                // Last bird kills all pigs (perfect score)
                game.setScreen(new winscreen1(game));
            } else if (launchbirdcount == 1) {
                // First bird kills all pigs
                game.setScreen(new winscreen3(game));
            } else if (launchbirdcount == 2) {
                // Two birds used to kill all pigs
                game.setScreen(new winscreen2(game));
            }

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
                    dragStartPosition.set(slingshotposition);
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

    private void checkPigSupport(float delta) {
        for (int i = pigs.size() - 1; i >= 0; i--) {
            pig currentPig = pigs.get(i);
            boolean hasSupport = false;

            // Check for support from blocks
            for (block currentBlock : blocks) {
                if (currentPig.x + currentPig.width > currentBlock.x &&
                    currentPig.x < currentBlock.x + currentBlock.width &&
                    currentPig.y > currentBlock.y &&
                    currentPig.y - currentBlock.y <= 1.1f) { // Adjusted tolerance for support
                    hasSupport = true;
                    break;
                }
            }

            if (!hasSupport) {
                currentPig.isfalling = true;
            } else {
                currentPig.isfalling = false;
                currentPig.velocity.y = 0; // Stop downward motion
            }

            if (currentPig.isfalling) {
                // Apply gravity and update pig's position
                currentPig.velocity.y += gravity * delta;
                currentPig.y += currentPig.velocity.y * delta;

                // Check for collisions with blocks below
                for (block currentBlock : blocks) {
                    if (currentPig.x + currentPig.width > currentBlock.x &&
                        currentPig.x < currentBlock.x + currentBlock.width &&
                        currentPig.y > currentBlock.y &&
                        currentPig.y + currentPig.velocity.y * delta <= currentBlock.y + currentBlock.height) {
                        // Settle the pig on top of the block
                        currentPig.y = currentBlock.y + currentBlock.height;
                        currentPig.isfalling = false;
                        currentPig.velocity.y = 0;
                        hasSupport = true;
                        break;
                    }
                }

                // If pig hits the ground
                if (!hasSupport && currentPig.y <= 0.4f) {
                    currentPig.y = 0.4f; // Settle on the ground
                    currentPig.isfalling = false;
                    currentPig.velocity.y = 0;
                    pigs.remove(i); // Remove the pig
                    killsound.play(); // Play the kill sound
                }
            }
        }
    }




    private void drawTrajectory() {
        shapeRenderer.setProjectionMatrix(viewport.getCamera().combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);

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


        if (birdPosition.y <= 0.4f) {
            birdPosition.y = 0.4f;
            birdVelocity.y *= -0.6f;
            currentbird.bouncecount--;

            if(currentbird.bouncecount<=0) {
                birds.remove(currentbird);
                handleNextBird();
                return;
            }
        }


        // Check for collisions with blocks
        for (int i = blocks.size()-1; i>=0; i--) {
            block currentBlock = blocks.get(i);
            if (collisionwithbox(currentbird, currentBlock)) {
                birdVelocity.x *= -0.8f;
                birdVelocity.y *= 0.8f;
                woodhit.play(4);
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
        for (int i = pigs.size()-1; i>=0; i--) {
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
        launchbirdcount++;
        if (!birds.isEmpty()) {
            bird nextBird = birds.get(0);
            nextBird.x = 0.69f;
            nextBird.y = 1.05f;
            birdPosition.set(nextBird.x, nextBird.y);
            dragStartPosition.set(nextBird.x,nextBird.y);
        }
    }

    boolean collisionwithbox(bird bird, block block){
        boolean iscollision = bird.x < block.x + block.width &&
            bird.x + bird.width > block.x &&
            bird.y < block.y + block.height &&
            bird.y + bird.height > block.y;
        return iscollision;
    }

    private boolean collisionwithpig(bird bird, pig pig){
        return bird.x < pig.x + pig.width &&
            bird.x + bird.width > pig.x &&
            bird.y < pig.y + pig.height &&
            bird.y + bird.height > pig.y;
    }


    private void createTower() {
        int tower1id = 1;
        int tower2id = 2;
        float baseX = 6.5f;
        float baseY = 0.5f;
        float blockWidth = 0.5f;
        float blockHeight = 0.5f;
        //baseline
        for (int i = 0; i < 4; i++) {
            blocks.add(new woodenbox(baseX + i * blockWidth, baseY, blockWidth, blockHeight,tower1id));
        }

        float glassx = 7.29f;
        float glassy = -0.074f;
        float blockwidth = 0.9f;
        float blockheight = 0.9f;
        float overlap = 0.39f;
        for (int i = 0; i < 2; i++) {
            blocks.add(new glassbox(glassx + i * (blockwidth-overlap), glassy + blockheight, blockwidth, blockheight,tower2id));
        }

        float woodx = 7.03f;
        float woody = 0.53f;
        float woodw = 0.48f;
        float woodh = 0.48f;
        blocks.add(new woodenbox(woodx + woodh, woody + 2 * woodh, woodw, woodh,tower1id));
    }


    private void draw() {
        ScreenUtils.clear(Color.BLACK);
        viewport.apply();
        batch.setProjectionMatrix(viewport.getCamera().combined);
        batch.begin();
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldWidth();
        batch.draw(nightbg, 0, 0, worldWidth, worldHeight);

        //slingshot dimension and position
        float slingshotx = 0.5f;
        float slingshoty = 0.42f;
        float slingwidht = 1;
        float slingheight = 0.8f;
        batch.draw(slingshot, slingshotx, slingshoty, slingwidht, slingheight);

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


        //pause button
        float pausex = 0.13f;
        float pausey = 4.6f;
        float pausewidht = 0.4f;
        float pauseheight = 0.4f;
        batch.draw(pausebutton, pausex, pausey, pausewidht, pauseheight);

        //save button
        float savex = 9.4f;
        float savey = 4.6f;
        float savewidht = 0.5f;
        float saveheight = 0.5f;
        batch.draw(savebutton,savex,savey,savewidht,saveheight);

        batch.end();


    }

    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        batch.dispose();
        nightbg.dispose();
        slingshot.dispose();
        pausebutton.dispose();
        killsound.dispose();
        stretch.dispose();
        release.dispose();
        woodhit.dispose();
    }
}

