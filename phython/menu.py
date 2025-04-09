import pickle
import os
import datetime
from Bezeroak import Bezeroak

FILE_NAME = 'clientes.pkl'

def save_data(data):
    with open(FILE_NAME, 'wb') as file:
        pickle.dump(data, file)

def load_data():
    if os.path.exists(FILE_NAME):
        with open(FILE_NAME, 'rb') as file:
            return pickle.load(file)
    return []

def add_item(my_list):
    bezeroa = Bezeroak("", "", "", "", "", "", "", "")
    bezeroa.setAtributte()
    my_list.append(bezeroa)
    save_data(my_list)

def view_list(my_list):
    if not my_list:
        print("Ez dago daturik.")
    else:
        for bezeroa in my_list:
            bezeroa.print()
            print()

def view_date_time():
    now = datetime.datetime.now()
    print("\nData eta ordua:", now.strftime("%Y-%m-%d %H:%M:%S"))

def id_filtratu(my_list):
    ida_bilatu = input("Sartu bilatu nahi duzun IDa: ")
    filtratuak = [bezeroa for bezeroa in my_list if bezeroa.getIda() == ida_bilatu]

    if filtratuak:
        print(f"\nID '{ida_bilatu}'-rekin bat datozen bezeroa:")
        for bezeroa in filtratuak:
            bezeroa.print()
            print()
    else:
        print(f"\nEz da bezerorik aurkitu '{ida_bilatu}' IDarekin.")

def menu_cliente():
    my_list = load_data()
    while True:
        print("\n  ========= Menu ========= ")
        view_date_time()
        print("1. Datuak gehitu")
        print("2. Datuak ikusi")
        print("3. Datuak billatu")
        print("4. Bueltatu Hasierara.")
        print("\n  ======= BEZEROAK ======= ")
        choice = input("\nZenbaki bat aukeratu (1-4): ")

        if choice == '1':
            add_item(my_list)
        elif choice == '2':
            view_list(my_list)
        elif choice == '3':
            id_filtratu(my_list)
        elif choice == '4':
            os.system("python Hasiera.py")
            break
        else:
            print("\nMezedez, sartu 1-4 era zenbaki bat.")

if __name__ == "__main__":
    menu_cliente()
