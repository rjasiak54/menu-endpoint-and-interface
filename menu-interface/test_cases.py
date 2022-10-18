test_cases_equality = [
    ("Breakfast 1,2,3",
    "Eggs, Toast, Coffee"),

    ("Breakfast 2,3,1",
    "Eggs, Toast, Coffee"),
    
    ("Breakfast 1,2,3,3,3",
    "Eggs, Toast, Coffee(3)"),

    ("Lunch 1,2,3",
    "Sandwich, Chips, Soda"),

    ("Lunch 1,2",
    "Sandwich, Chips, Water"),

    ("Lunch 1,2,2",
    "Sandwich, Chips(2), Water"),

    ("Dinner 1,2,3,4",
    "Steak, Potatoes, Wine, Water, Cake"),

]

test_cases_inequality = [
    ("Dinner 1,2,3",
    "Steak, Potatoes, Wine, Water, Cake"),

    ("Lunch 1,1,2,3",
    "Sandwich, Chips, Soda"),

    ("Breakfast 1",
    "Eggs, Toast, Coffee"),

    ("Lunch",
    "Sandwich, Chips, Soda")

]