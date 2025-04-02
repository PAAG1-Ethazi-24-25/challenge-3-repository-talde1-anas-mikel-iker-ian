import pickle
import os
import datetime

clientes = 'clientes.pkl'

def save_data(data):
    with open(clientes, 'wb') as file:
        pickle.dump(data, file)

def load_data():
    if os.path.exists(clientes):
        with open(clientes, 'rb') as file:
            return pickle.load(file)
    return []

def add_item(my_list):
    loop = 'bai'
    while loop == 'bai':
        cliente = {
            'ID': input("ID Sartu: "),
            'IZENA': input("IZENA Sartu: "),
            'EMAIL': input("EMAIL Sartu: "),
            'TELEFONOA': input("TELEFONOA Sartu: "),
            'HERRIA': input("HERRIA Sartu: "),
            'POSTAKODEA': input("POSTAKODEA Sartu: "),
            'DIREKZIOA': input("DIREKZIOA Sartu: "),
            'ERREGISTROA': input("ERREGISTROA Sartu: ")
        }
        my_list.append(cliente)
        save_data(my_list)
        loop = input("Beste erregistro bat sartu nahi duzu? (bai/ez): ")
        while loop not in ["bai", "ez"]:
            loop = input("Barkatu baina ez dut ulertu. Beste pertsona bat sartu nahi duzu? (bai/ez): ")

def view_list(my_list):
    if not my_list:
        print("Ez dago daturik.")
    else:
        for cliente in my_list:
            print(
                f"{cliente['ID']:9},{cliente['IZENA']:10},{cliente['EMAIL']:10},{cliente['TELEFONOA']:9},"
                f"{cliente['HERRIA']:10},{cliente['POSTAKODEA']:10},{cliente['DIREKZIOA']:10},{cliente['ERREGISTROA']:10}"
            )

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
        print("3. Itxi.")
        choice = input("\nZenbaki bat aukeratu (1-3): ")

        if choice == '1':
            add_item(my_list)
        elif choice == '2':
            view_list(my_list)
        elif choice == '3':
            print("\nEgun ona izan!")
            break
        else:
            print("\nMezedez, sartu 1-3 era zenbaki bat.")

if __name__ == "__main__":
    menu_cliente()
