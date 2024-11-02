import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;


@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    @Mock
    Bun bun;

    @Mock
    Ingredient ingredient;

    @Mock
    Ingredient ingredientt;

    @Test
    public void setBunsTest() {
        Burger testBurger = new Burger();
        testBurger.setBuns(bun);
        Assert.assertEquals(bun, testBurger.bun);

    }

    @Test
    public void addIngredientTest() {
        Burger testBurger = new Burger();
        testBurger.addIngredient(ingredient);
        Assert.assertEquals(1, testBurger.ingredients.size());
        Assert.assertEquals(ingredient, testBurger.ingredients.get(0));
    }

    @Test
    public void removeIngredientTest() {
        Burger testBurger = new Burger();
        testBurger.addIngredient(ingredient);
        testBurger.removeIngredient(0);
        Assert.assertEquals(0, testBurger.ingredients.size());

    }

    @Test
    public void getPriceSeveralIngredientsBurgerTest() {
        Burger testBurger = new Burger();
        testBurger.setBuns(bun);
        testBurger.addIngredient(ingredient);
        testBurger.addIngredient(ingredientt);
        Mockito.when(bun.getPrice()).thenReturn(10F);
        Mockito.when(ingredient.getPrice()).thenReturn(20F);
        Mockito.when(ingredientt.getPrice()).thenReturn(40F);
        Assert.assertEquals(80, testBurger.getPrice(), 0);

    }

    @Test
    public void moveIngredientTest() {
        Burger testBurger = new Burger();
        testBurger.addIngredient(ingredient);
        testBurger.addIngredient(ingredientt);
        testBurger.moveIngredient(0, 1);
        Assert.assertEquals(ingredient, testBurger.ingredients.get(1));
        Assert.assertEquals(ingredientt, testBurger.ingredients.get(0));

    }

    @Test
    public void getPriceOneIngredientBurgerTest() {
        Burger testBurger = new Burger();
        testBurger.setBuns(bun);
        testBurger.addIngredient(ingredient);
        Mockito.when(bun.getPrice()).thenReturn(10F);
        Mockito.when(ingredient.getPrice()).thenReturn(20F);
        Assert.assertEquals(40, testBurger.getPrice(), 0);

    }

    @Test
    public void getReceiptOneIngredientBurgerTest() {
        Burger testBurger = new Burger();
        testBurger.setBuns(bun);
        testBurger.addIngredient(ingredient);
        Mockito.when(bun.getPrice()).thenReturn(10F);
        Mockito.when(ingredient.getPrice()).thenReturn(20F);
        Mockito.when(ingredient.getName()).thenReturn("chili sauce");
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);

        Assert.assertEquals(
                String.format("(==== %s ====)%n", testBurger.bun.getName()) +
                        String.format("= %s %s =%n", testBurger.ingredients.get(0).getType().toString().toLowerCase(),
                                testBurger.ingredients.get(0).getName()) +
                        String.format("(==== %s ====)%n", bun.getName()) +
                        String.format("%nPrice: %f%n", testBurger.getPrice()),
                testBurger.getReceipt());

    }

    @Test
    public void getReceiptSeveralIngredientsBurgerTest() {
        Burger testBurger = new Burger();
        testBurger.setBuns(bun);
        testBurger.addIngredient(ingredient);
        testBurger.addIngredient(ingredientt);
        Mockito.when(bun.getPrice()).thenReturn(10F);
        Mockito.when(ingredient.getPrice()).thenReturn(20F);
        Mockito.when(ingredientt.getPrice()).thenReturn(100F);
        Mockito.when(ingredient.getName()).thenReturn("chili sauce");
        Mockito.when(bun.getName()).thenReturn("black bun");
        Mockito.when(ingredientt.getName()).thenReturn("sausage");
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredientt.getType()).thenReturn(IngredientType.FILLING);

        Assert.assertEquals(
                String.format("(==== %s ====)%n", testBurger.bun.getName()) +
                        String.format("= %s %s =%n", testBurger.ingredients.get(0).getType().toString().toLowerCase(),
                                testBurger.ingredients.get(0).getName()) +
                        String.format("= %s %s =%n", testBurger.ingredients.get(1).getType().toString().toLowerCase(),
                                testBurger.ingredients.get(1).getName()) +
                        String.format("(==== %s ====)%n", bun.getName()) +
                        String.format("%nPrice: %f%n", testBurger.getPrice()),
                testBurger.getReceipt());

    }
}