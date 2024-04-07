package com.ocado.basket;

import java.util.Arrays;
import java.util.List;

public class Dictionary {

    public static final String SAME_DAY_DELIVERY = "Same day delivery";
    public static final String COURIER = "Courier";
    public static final String EXPRESS_COLLECTION = "Express Collection";
    public static final String MAILBOX_DELIVERY = "Mailbox delivery";
    public static final String PICK_UP_POINT = "Pick-up point";
    public static final String NEXT_DAY_SHIPPING = "Next day shipping";
    public static final String IN_STORE_PICK_UP = "In-store pick-up";

    public static final String SAUCE_MINT = "Sauce - Mint";
    public static final String NUMI_TEA = "Numi - Assorted Teas";
    public static final String GARLIC_PEELED = "Garlic - Peeled";
    public static final String CAKE_CHEESECAKE = "Cake - Miini Cheesecake Cherry";
    public static final String FOND_CHOCOLATE = "Fond - Chocolate";
    public static final String CHOCOLATE_UNSWEETENED = "Chocolate - Unsweetened";
    public static final String NUT_ALMOND = "Nut - Almond, Blanched, Whole";
    public static final String HAGGIS = "Haggis";
    public static final String MUSHROOM_FROZEN = "Mushroom - Porcini Frozen";
    public static final String LONGAN = "Longan";
    public static final String BAG_CLEAR_10LB = "Bag Clear 10 Lb";
    public static final String NANTUCKET_POMEGRANATE = "Nantucket - Pomegranate Pear";
    public static final String PUREE_STRAWBERRY = "Puree - Strawberry";
    public static final String APPLES_SPARTAN = "Apples - Spartan";
    public static final String CABBAGE_NAPPA = "Cabbage - Nappa";
    public static final String BAGEL_WHITE_SESAME = "Bagel - Whole White Sesame";
    public static final String TEA_GREEN_APPLE = "Tea - Apple Green Tea";
    public static final String BREAD_FLAT_BREAD = "Bread - Flat Bread";
    public static final String BEER_MUSKOKA_CREAM_ALE = "Beer - Muskoka Cream Ale";
    public static final String SPINACH_FROZEN = "Spinach - Frozen";
    public static final String CHEESE_MIX = "Cheese - Mix";
    public static final String CARBONATED_WATER_RASPBERRY = "Carbonated Water - Raspberry";
    public static final String NANTUCKET_APPLE_JUICE = "Nantucket Apple Juice";
    public static final String OXTAIL_CUT = "Oxtail - Cut";
    public static final String CHEESE_SHEEP_MILK = "Cheese - Sheep Milk";
    public static final String CHICKEN_PHYLLO = "Chickhen - Chicken Phyllo";
    public static final String DRIED_PEACH = "Dried Peach";

    public List<String> getPickUpPointList() {
        return Arrays.asList(
                "Juice - Ocean Spray Cranberry", "Fond - Chocolate", "Nut - Almond, Blanched, Whole",
                "Cookies - Englishbay Wht", "Chickhen - Chicken Phyllo", "Cheese Cloth", "Cabbage - Nappa",
                "Onions - White", "Gatorade - Lemon Lime", "Carbonated Water - Raspberry", "Pork Salted Bellies",
                "Sauce - Mint", "Pepper - Green, Chili", "Cookies Oatmeal Raisin", "Gingerale - Diet - Schweppes"
        );
    }

    public List<String> getInStorePickUpList() {
        return Arrays.asList(
                "Compound - Mocha", "Fish - Soup Base, Bouillon", "Pork Ham Prager", "Yogurt - Cherry, 175 Gr",
                "Cheese - St. Andre", "Butter - Salted, Micro", "Emulsifier", "Appetizer - Escargot Puff",
                "Bread - Crumbs, Bulk", "Brandy - Bar", "Beer - Alexander Kieths, Pale Ale", "Wakami Seaweed",
                "Capers - Ox Eye Daisy", "Puree - Guava", "Flavouring - Rum", "Pepper - Julienne, Frozen",
                "Oil - Olive, Extra Virgin", "Beef Cheek Fresh", "Ecolab - Medallion", "Cookie - Oreo 100x2",
                "Shrimp - 21/25, Peel And Deviened", "Dc Hikiage Hira Huba", "Tea - Apple Green Tea", "Apples - Spartan",
                "Lamb - Whole, Fresh", "English Muffin", "Puree - Strawberry", "Numi - Assorted Teas", "Cake Circle, Paprus",
                "Oxtail - Cut", "Syrup - Monin - Blue Curacao", "Bread - Flat Bread", "Crush - Orange, 355ml",
                "Cloves - Ground", "Sauce - Salsa", "Ocean Spray - Ruby Red", "Wine - Champagne Brut Veuve", "Beans - Green",
                "V8 Splash Strawberry Banana", "Chocolate - Unsweetened", "Peach - Fresh", "Garbage Bags - Clear",
                "Cocoa Butter", "Fudge - Chocolate Fudge", "The Pop Shoppe - Grape", "Mushroom - Porcini Frozen",
                "Otomegusa Dashi Konbu", "Pork - Hock And Feet Attached", "Haggis", "Sole - Dover, Whole, Fresh",
                "Juice - Apple, 1.36l", "Longan", "Bag Clear 10 Lb", "Flour - Buckwheat, Dark",
                "Wine - Port Late Bottled Vintage", "Pineapple - Canned, Rings", "Vinegar - Red Wine", "Cheese - Mix",
                "Flower - Daisies", "Nantucket Apple Juice", "Bread - Petit Baguette", "Beer - Muskoka Cream Ale",
                "Mustard - Dry, Powder", "Bagel - Whole White Sesame", "Soup - Campbells Mac N Cheese"
        );
    }

    public List<String> getSameDayDeliveryList() {
        return Arrays.asList(
                "Garlic - Peeled", "Sugar - Cubes", "Wine - Fontanafredda Barolo", "Mix - Cocktail Ice Cream",
                "Wine - Magnotta - Cab Sauv", "Nantucket - Pomegranate Pear", "Longos - Chicken Curried",
                "Dried Peach", "Pepper - Red, Finger Hot"
        );
    }

    public List<String> getCourierList() {
        return Arrays.asList(
                "Steam Pan - Half Size Deep", "Cake - Miini Cheesecake Cherry", "Tart - Raisin And Pecan",
                "Salt - Rock, Course", "Table Cloth 54x72 White", "Pasta - Fusili Tri - Coloured"
        );
    }

    public List<String> getMailboxDeliveryList() {
        return Arrays.asList(
                "Spinach - Frozen", "Corn Syrup", "Cheese - Sheep Milk", "Wine - Sherry Dry Sack, William"
        );
    }

    public List<String> getNextDayShippingList() {
        return List.of(
                "Energy Drink - Redbull 355ml"
        );
    }
}
