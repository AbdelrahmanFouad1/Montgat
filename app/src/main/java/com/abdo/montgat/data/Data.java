package com.abdo.montgat.data;

import com.abdo.montgat.R;
import com.abdo.montgat.model.AboutUsModel;
import com.abdo.montgat.model.CategoryHomeModel;
import com.abdo.montgat.model.ProductModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {

    public static final int T_SHIRT_FRAGMENT_HOME_CODE = 10;
    public static final int SHIRT_FRAGMENT_HOME_CODE = 11;
    public static final int SUNGLASSES_FRAGMENT_HOME_CODE = 12;
    public static final int COAT_FRAGMENT_HOME_CODE = 13;
    public static final int SHOES_FRAGMENT_HOME_CODE = 14;
    public static final int TROUSERS_FRAGMENT_HOME_CODE = 15;
    public static final int CAP_FRAGMENT_HOME_CODE = 16;


    public List<Integer> getDataFromSlider() {

        List<Integer> image = new ArrayList<>();

        image.add(R.drawable.banner_one);
        image.add(R.drawable.banner_one2);
        image.add(R.drawable.banner_one3);
        image.add(R.drawable.banner_one5);


        return image;
    }

    public List<CategoryHomeModel> getDataFromCategory() {

        List<CategoryHomeModel> category = new ArrayList<>();

        category.add(new CategoryHomeModel("T-shirt", R.drawable.ic_t_shirt, T_SHIRT_FRAGMENT_HOME_CODE));
        category.add(new CategoryHomeModel("Shirt", R.drawable.ic_shirts, SHIRT_FRAGMENT_HOME_CODE));
        category.add(new CategoryHomeModel("Sunglasses", R.drawable.ic_sunglasses, SUNGLASSES_FRAGMENT_HOME_CODE));
        category.add(new CategoryHomeModel("Coat", R.drawable.ic_coat, COAT_FRAGMENT_HOME_CODE));
        category.add(new CategoryHomeModel("Shoes", R.drawable.ic_shose, SHOES_FRAGMENT_HOME_CODE));
        category.add(new CategoryHomeModel("Trouser", R.drawable.ic_trouser, TROUSERS_FRAGMENT_HOME_CODE));
        category.add(new CategoryHomeModel("Cap", R.drawable.ic_cap, CAP_FRAGMENT_HOME_CODE));
        return category;
    }

    public List<String> getDataFromCategorySheet() {

        List<String> item = new ArrayList<>();

        item.add("T-shirt");
        item.add("Shirt");
        item.add("Sunglasses");
        item.add("Coat");
        item.add("Shoes");
        item.add("Trouser");
        item.add("Cap");


        return item;
    }

    public List<String> getDataFromSizeSheet() {

        List<String> item = new ArrayList<>();

        item.add("S");
        item.add("M");
        item.add("L");
        item.add("XL");
        item.add("XXL");
        item.add("XXXL");

        return item;
    }

    public List<String> getDataFromGenderSheet() {

        List<String> item = new ArrayList<>();

        item.add("Male");
        item.add("Female");
        item.add("Other");


        return item;
    }

    //////////////////////////////////////////////////////////////////////////
    public List<ProductModel> getDataFromT_Shirt() {

        List<ProductModel> tShirt = new ArrayList<>();

        tShirt.add(new ProductModel("0","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "250",
                "300 EGP",
                R.drawable.t_shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        tShirt.add(new ProductModel("1","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "250",
                "300 EGP",
                R.drawable.t_shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        tShirt.add(new ProductModel("2","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "250",
                "300 EGP",
                R.drawable.t_shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        tShirt.add(new ProductModel("3","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "250",
                "300 EGP",
                R.drawable.t_shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        tShirt.add(new ProductModel("4","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "250",
                "300 EGP",
                R.drawable.t_shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        tShirt.add(new ProductModel("5","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "250",
                "300 EGP",
                R.drawable.t_shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        tShirt.add(new ProductModel("6","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "250",
                "300 EGP",
                R.drawable.t_shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        tShirt.add(new ProductModel("7","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "250",
                "300 EGP",
                R.drawable.t_shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        tShirt.add(new ProductModel("8","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "250",
                "300 EGP",
                R.drawable.t_shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        tShirt.add(new ProductModel("9","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "250",
                "300 EGP",
                R.drawable.t_shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));

        return tShirt;
    }

    public List<ProductModel> getDataFromShirt() {

        List<ProductModel> shirt = new ArrayList<>();


        shirt.add(new ProductModel("10","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "400",
                "500 EGP",
                R.drawable.shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shirt.add(new ProductModel("11","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "400",
                "500 EGP",
                R.drawable.shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shirt.add(new ProductModel("12","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "400",
                "500 EGP",
                R.drawable.shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shirt.add(new ProductModel("13","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "400",
                "500 EGP",
                R.drawable.shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shirt.add(new ProductModel("14","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "400",
                "500 EGP",
                R.drawable.shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shirt.add(new ProductModel("15","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "400",
                "500 EGP",
                R.drawable.shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shirt.add(new ProductModel("16","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "400",
                "500 EGP",
                R.drawable.shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shirt.add(new ProductModel("17","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "400",
                "500 EGP",
                R.drawable.shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shirt.add(new ProductModel("18","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "400",
                "500 EGP",
                R.drawable.shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shirt.add(new ProductModel("19","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "400",
                "500 EGP",
                R.drawable.shirt,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));


        return shirt;
    }

    public List<ProductModel> getDataFromSunglasses() {

        List<ProductModel> sunglasses = new ArrayList<>();

        sunglasses.add(new ProductModel("20","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "650",
                "650 EGP",
                R.drawable.sunglasses,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        sunglasses.add(new ProductModel("21","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "650",
                "650 EGP",
                R.drawable.sunglasses,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        sunglasses.add(new ProductModel("22","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "650",
                "650 EGP",
                R.drawable.sunglasses,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        sunglasses.add(new ProductModel("23","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "650",
                "650 EGP",
                R.drawable.sunglasses,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        sunglasses.add(new ProductModel("24","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "650",
                "650 EGP",
                R.drawable.sunglasses,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        sunglasses.add(new ProductModel("25","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "650",
                "650 EGP",
                R.drawable.sunglasses,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        sunglasses.add(new ProductModel("26","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "650",
                "650 EGP",
                R.drawable.sunglasses,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        sunglasses.add(new ProductModel("27","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "650",
                "650 EGP",
                R.drawable.sunglasses,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        sunglasses.add(new ProductModel("28","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "650",
                "650 EGP",
                R.drawable.sunglasses,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        sunglasses.add(new ProductModel("29","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "650",
                "650 EGP",
                R.drawable.sunglasses,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        sunglasses.add(new ProductModel("30","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "650",
                "650 EGP",
                R.drawable.sunglasses,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        sunglasses.add(new ProductModel("31","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "650",
                "650 EGP",
                R.drawable.sunglasses,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));

        return sunglasses;
    }

    public List<ProductModel> getDataFromCoat() {

        List<ProductModel> coat = new ArrayList<>();

        coat.add(new ProductModel("32","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "750",
                "900 EGP",
                R.drawable.coat, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        coat.add(new ProductModel("33","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "750",
                "900 EGP",
                R.drawable.coat, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        coat.add(new ProductModel("34","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "750",
                "900 EGP",
                R.drawable.coat, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        coat.add(new ProductModel("35","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "750",
                "900 EGP",
                R.drawable.coat, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        coat.add(new ProductModel("36","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "750",
                "900 EGP",
                R.drawable.coat, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        coat.add(new ProductModel("37","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "750",
                "900 EGP",
                R.drawable.coat, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        coat.add(new ProductModel("38","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "750",
                "900 EGP",
                R.drawable.coat, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        coat.add(new ProductModel("39","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "750",
                "900 EGP",
                R.drawable.coat, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        coat.add(new ProductModel("40","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "750",
                "900 EGP",
                R.drawable.coat, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));

        return coat;
    }

    public List<ProductModel> getDataFromShoes() {

        List<ProductModel> shoes = new ArrayList<>();

        shoes.add(new ProductModel("41","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "550",
                "600 EGP",
                R.drawable.shoes,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shoes.add(new ProductModel("42","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "550",
                "600 EGP",
                R.drawable.shoes,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shoes.add(new ProductModel("43","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "550",
                "600 EGP",
                R.drawable.shoes,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shoes.add(new ProductModel("44","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "550",
                "600 EGP",
                R.drawable.shoes,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shoes.add(new ProductModel("45","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "550",
                "600 EGP",
                R.drawable.shoes,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shoes.add(new ProductModel("46","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "550",
                "600 EGP",
                R.drawable.shoes,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shoes.add(new ProductModel("47","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "550",
                "600 EGP",
                R.drawable.shoes,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shoes.add(new ProductModel("48","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "550",
                "600 EGP",
                R.drawable.shoes,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shoes.add(new ProductModel("49","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "550",
                "600 EGP",
                R.drawable.shoes,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shoes.add(new ProductModel("50","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "550",
                "600 EGP",
                R.drawable.shoes,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shoes.add(new ProductModel("51","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "550",
                "600 EGP",
                R.drawable.shoes,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        shoes.add(new ProductModel("52","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "550",
                "600 EGP",
                R.drawable.shoes,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));

        return shoes;
    }

    public List<ProductModel> getDataFromTrousers() {

        List<ProductModel> trousers = new ArrayList<>();

        trousers.add(new ProductModel("53","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("54","0","0", "British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("55","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("56","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("57","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("58","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("59","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("60","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("61","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("62","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("63","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("64","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("65","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("66","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trousers.add(new ProductModel("67","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "480",
                "600 EGP",
                R.drawable.trousers,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));

        return trousers;
    }

    public List<ProductModel> getDataFromCap() {

        List<ProductModel> cap = new ArrayList<>();

        cap.add(new ProductModel("68","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("69","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("70","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("71","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("72","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("73","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("74","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("75","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("76","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("77","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("78","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("79","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("80","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("81","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("82","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        cap.add(new ProductModel("83","0", "0","British Menswear Brands",
                "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.",
                "150",
                "200 EGP",
                R.drawable.cap,
                Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"),
                Arrays.asList(R.color.red, R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));

        return cap;
    }

    public List<ProductModel> getDataFromTrending() {

        List<ProductModel> trend = new ArrayList<>();

        trend.add(new ProductModel("84", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "400", "500 EGP", R.drawable.woman, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.red, R.color.gray, R.color.black, R.color.blue, R.color.tea, R.color.main_yellow, R.color.white_blue, R.color.burble)));
        trend.add(new ProductModel("85", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "200", "300 EGP", R.drawable.man2, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.red, R.color.gray, R.color.black, R.color.blue, R.color.tea, R.color.main_yellow, R.color.white_blue, R.color.burble)));
        trend.add(new ProductModel("86", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "350", "400 EGP", R.drawable.woman2, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.gray, R.color.black, R.color.blue, R.color.tea, R.color.main_yellow, R.color.white_blue)));
        trend.add(new ProductModel("87", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "780", "800 EGP", R.drawable.woman3, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.black, R.color.blue, R.color.tea, R.color.main_yellow, R.color.white_blue, R.color.burble)));
        trend.add(new ProductModel("88", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "390", "400 EGP", R.drawable.woman4, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.tea, R.color.main_yellow, R.color.white_blue, R.color.burble)));
        trend.add(new ProductModel("89", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "275", "300 EGP", R.drawable.woman9, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        trend.add(new ProductModel("90", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "625", "700 EGP", R.drawable.man3, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.black, R.color.blue, R.color.tea, R.color.main_yellow, R.color.white_blue)));


        return trend;
    }

    public List<ProductModel> getDataFromNewArrival() {

        List<ProductModel> newArrival = new ArrayList<>();

        newArrival.add(new ProductModel("91", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "125", "200 EGP", R.drawable.man, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.red, R.color.gray, R.color.black, R.color.blue, R.color.tea, R.color.main_yellow, R.color.white_blue, R.color.burble)));
        newArrival.add(new ProductModel("92", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "690", "700 EGP", R.drawable.woman5, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.red, R.color.gray, R.color.black, R.color.blue, R.color.tea, R.color.main_yellow, R.color.white_blue, R.color.burble)));
        newArrival.add(new ProductModel("93", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "360", "400 EGP", R.drawable.man4, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.gray, R.color.black, R.color.blue, R.color.tea, R.color.main_yellow, R.color.white_blue)));
        newArrival.add(new ProductModel("94", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "975", "1000 EGP", R.drawable.man5, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.black, R.color.blue, R.color.tea, R.color.main_yellow, R.color.white_blue, R.color.burble)));
        newArrival.add(new ProductModel("95", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "610", "700 EGP", R.drawable.woman6, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.tea, R.color.main_yellow, R.color.white_blue, R.color.burble)));
        newArrival.add(new ProductModel("96", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "820", "900 EGP", R.drawable.man6, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.gray, R.color.blue, R.color.tea, R.color.white_blue, R.color.burble)));
        newArrival.add(new ProductModel("97", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "260", "300 EGP", R.drawable.woman7, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.black, R.color.blue, R.color.tea, R.color.main_yellow, R.color.white_blue)));
        newArrival.add(new ProductModel("98", "0", "0", "British Menswear Brands", "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout.", "650", "700 EGP", R.drawable.woman8, Arrays.asList("S", "M", "L", "XL", "XXL", "XXXL"), Arrays.asList(R.color.red, R.color.gray, R.color.black, R.color.blue, R.color.tea, R.color.main_yellow, R.color.white_blue)));

        return newArrival;
    }
    /////////////////////////////////////////////////////////////////////////////////////////


    public List<AboutUsModel> getDataFromAboutUs() {

        List<AboutUsModel> about = new ArrayList<>();

        about.add(new AboutUsModel("Lorem Ipsum Title one", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. "));
        about.add(new AboutUsModel("Lorem Ipsum Title two", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        about.add(new AboutUsModel("Lorem Ipsum Title one", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. "));
        about.add(new AboutUsModel("Lorem Ipsum Title two", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        about.add(new AboutUsModel("Lorem Ipsum Title one", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. "));
        about.add(new AboutUsModel("Lorem Ipsum Title two", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        about.add(new AboutUsModel("Lorem Ipsum Title one", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. "));
        about.add(new AboutUsModel("Lorem Ipsum Title two", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        about.add(new AboutUsModel("Lorem Ipsum Title one", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. "));
        about.add(new AboutUsModel("Lorem Ipsum Title two", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        about.add(new AboutUsModel("Lorem Ipsum Title one", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. "));
        about.add(new AboutUsModel("Lorem Ipsum Title two", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        about.add(new AboutUsModel("Lorem Ipsum Title one", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. "));
        about.add(new AboutUsModel("Lorem Ipsum Title two", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        about.add(new AboutUsModel("Lorem Ipsum Title one", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. "));
        about.add(new AboutUsModel("Lorem Ipsum Title two", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        about.add(new AboutUsModel("Lorem Ipsum Title one", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. "));
        about.add(new AboutUsModel("Lorem Ipsum Title two", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        about.add(new AboutUsModel("Lorem Ipsum Title one", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. "));
        about.add(new AboutUsModel("Lorem Ipsum Title two", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));

        return about;
    }

}
