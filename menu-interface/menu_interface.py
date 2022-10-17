import requests
import json
import traceback

import menu_interface_config

class menu_interface:

    def __init__(self):
        self.menu_endpoint = menu_interface_config.API_ENDPOINT


    def __assemble_endpoint(self, meal:str, order_nums:str) -> str:
        return f"{self.menu_endpoint}{meal}/{order_nums}/"


    def get_full_menu(self) -> dict:
        try:
            response = requests.get(self.menu_endpoint)
            body = response.json()
            return body
        except Exception as e:
            return "Something went wrong in request..."+str(e)+traceback.format_exc()

    def requestOrder(self, order) -> str:
        order_split = order.split()
        meal = order_split[0]

        order_nums = ""
        for possible_piece in order_split[1:]:
            order_nums += possible_piece.replace(",","")

        endpoint = self.__assemble_endpoint(meal,order_nums)
        try:
            response = requests.get(endpoint)
            body = response.json()
            to_print = ', '.join(body)
            return to_print
        except Exception as e:
            return "Something went wrong in request..."+str(e)+traceback.format_exc()



def print_sub_menu(menu, meal):
    try:
        print(f"{meal}:")
        print(json.dumps(menu[meal],indent=2))
    except:
        print(json.dumps(menu))



def main() -> None:

    mi = menu_interface()

    while True:
        order = input("Enter order, m for menu, q to quit: ")
        
        if order == 'q': break
            
        if order == 'm':
            menu = mi.get_full_menu()
            print_sub_menu(menu,"Breakfast")
            print_sub_menu(menu,"Lunch")
            print_sub_menu(menu,"Dinner")

        else:
            food = mi.requestOrder(order)
            print(food)



if __name__ == '__main__':
    main()