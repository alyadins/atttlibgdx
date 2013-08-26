package ru.petrsu.attt.view;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ru.petrsu.attt.Assets;
import ru.petrsu.attt.model.SmallFieldModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: lexer
 * Date: 8/26/13
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class ZoomedField extends View {
    SmallFieldModel model;
    Sprite field;
    List<CrosZero> crosZeros;

    Random random = new Random();

    public ZoomedField(Sprite field, SmallFieldModel model) {
        super(field.getWidth(), field.getHeight());
        this.model = model;
        this.type = Type.FIELD;
        crosZeros = new ArrayList<CrosZero>();
        this.field = field;
        initCrosZeros();
    }

    private void initCrosZeros() {
        for (int i = 0; i < 9; i ++) {
            crosZeros.add(new CrosZero(width / 3, height / 3, CrosZero.Type.NONE));
            crosZeros.get(i).setSprites(new Sprite(Assets.bigCros),
                    new Sprite(Assets.bigZero));
            CrosZero cz = crosZeros.get(i);
            int s = random.nextInt(3);
            switch (s) {
                case 0:
                    cz.setType(CrosZero.Type.NONE);
                    break;
                case 1:
                    cz.setType(CrosZero.Type.CROS);
                    break;
                case 2:
                    cz.setType(CrosZero.Type.ZERO);
            }
        }
    }

    @Override
    public void draw(SpriteBatch spriteBatch) {
        field.draw(spriteBatch);
        for (int i = 0; i < 9; i++) {
            crosZeros.get(i).draw(spriteBatch);
        }
    }

    @Override
    public void update(float delta) {
        field.setPosition(x, y);
        field.setSize(width, height);
        for (int i = 0; i < 9; i++) {
            CrosZero cz = crosZeros.get(i);
            cz.setPosition(x + (width / 3) * (i / 3),
                    y + (height / 3) * (i % 3));
            cz.setAbsoluteSize(width / 3, height / 3);
            cz.update(delta);
        }
    }
}
