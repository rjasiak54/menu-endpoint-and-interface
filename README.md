# Menu Endpoint and Interface

## Goal

The goal if this project is a create a system that takes orders for breakfast, lunch, and dinner, with a few requirements:

- Object Oriented
- Extensible
- Testable
- Well-Organized


## Solution

### Structure (Brief)

The project contains 2 programs: a Springboot API, and a simple python-interface for the API.

The Springboot API, `menu-endpoint/` connects to a Mongo Database where the menu is stored, as well as handles all rule-based logic for ordering from the menu. The API's endpoint can be invoked via 2 paths:

- `/menu/`
- `/menu/<meal>/<order_as_strings>/`

Usage example:
- `http://ec2-13-58-34-18.us-east-2.compute.amazonaws.com:8080/menu/Breakfast/123/`

</br>

The `menu-interface/` is simply a wrapper for calling that API. Ideally, it's in place of a web-app or other GUI.

Upon running the interface, you will be prompted with entering some input. The form of the input should be as follows:
- `<meal> <series of comma separated numbers>`
    - example: `Breakfast 1,2,3`



## Running Locally

### Menu API

No need! A copy of the API is deployed on an AWS EC2 instance, with it's endpoint listed here:
- `http://ec2-13-58-34-18.us-east-2.compute.amazonaws.com:8080/menu/`
- **Please Note:** The EC2 instance will be terminated on Thursday, Oct 20 

You *could* run it locally, but you'll need to configure a mongo database in the code... so it'd be easier to just use the EC2 API.


### Menu Interface

The menu interface is already configured to connect to the EC2 instance, so you should be able to simply run it.

- `cd` into the `menu-interface/` directory.
- Ensure you have these requirements:
    - `requests`
    -  `pytest`
- Run with:
    - `$ python3 menu_interface.py`
- Test various inputs with:
    - `$ pytest test_menu_interface.py`
- **Note:** Both running and testing require internet connection!!!


## Object Orientedness

The solution consists of 2 objects- the rule-based menu API, and the API interface. Additionally, each of the programs utilizes classes to seperate and couple processes.


## Extensibility

The use of MongoDB allows for more data to added easily. Additionally, the deployment of the API on AWS EC2 allows for the growth of users to be relatively stable for a period of time.

## Testability

The API allows for ease of testing by making calls to it and comparing with the expected result.

</br>


## Current Limitations

### Mongo Instance

The mongo cluster is *on* the EC2 instance. This certainly is not ideal long-term, but works for the time being. Ideally, it'd be in Mongo Atlas or something similar.

### No Development Pipeline

The .jar file was uploaded to EC2 manually, and the mongo data was inserted manually. Automation should be set up for both of these things.

### Hacky API Code

If you look inside the source files in `menu-endpoint/`, you'll see that the code isn't quite as clean as can be, doesn't follow best conventions, etc. 

### Incomplete Test Cases

Test Cases have not been written for the classes and methods within the API- only for the API as a whole.

