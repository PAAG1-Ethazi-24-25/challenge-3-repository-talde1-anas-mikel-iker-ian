import pickle
import os
import datetime
from Produktoak import Produktoak

FILE_NAME = 'Produktoak.pkl'

def save_data(data):
    with open(FILE_NAME, 'wb') as file:
        pickle.dump(data, file)

def load_data():
    if os.path.exists(FILE_NAME):
        with open(FILE_NAME, 'rb') as file:
            return pickle.load(file)
    return []

def add_item(my_list):
    Produkto = Produktoak("", "", "", "", "", "", "", "")
    Produkto.setAtributte()
    my_list.append(Produkto)
    save_data(my_list)

def view_list(my_list):
    if not my_list:
        print("Ez dago daturik.")
    else:
        for Produkto in my_list:
            Produkto.print()
            print()

def view_date_time():
    now = datetime.datetime.now()
    print("\nFecha y hora actuales:", now.strftime("%Y-%m-%d %H:%M:%S"))

def menu_cliente():
    my_list = load_data()
    while True:
        print("\n  ========= Menu ========= ")
        view_date_time()
        print("1. Datuak gehitu")
        print("2. Datuak ikusi")
        print("3. Bueltatu Hasierara.")
        print("\n  ======= PRODUKTOAK ====== ")
        choice = input("\nZenbaki bat aukeratu (1-3): ")

        if choice == '1':
            add_item(my_list)
        elif choice == '2':
            view_list(my_list)
        elif choice == '3':
            os.system("python Hasiera.py")
        else:
            print("\nMezedez, sartu 1-3 era zenbaki bat.")

if __name__ == "__main__":
    menu_cliente()
