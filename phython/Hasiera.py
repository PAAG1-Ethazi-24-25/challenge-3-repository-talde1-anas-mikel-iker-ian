import datetime
import os

def show_menu():
    print("\n ========= HASIERAKO MENUA ========= ")
    now = datetime.datetime.now()
    print("\nFecha y hora actuales:", now.strftime("%Y-%m-%d %H:%M:%S"))
    print("1.  PRODUKTOAK")
    print("2.  LANGILEAK")
    print("3.  ERABILTZAILEAK")
    print("4.  SALMENTAK")
    print("5.  IRTEN")

def main():
    while True:
        show_menu()
        choice = input("\nSeleccione una opción (1-5): ")

        if choice == '1':
            os.system("python Produktoakmenu.py")
            break
        elif choice == '2':
            os.system("python Langilemenu.py")
            break
        elif choice == '3':
            os.system("python menu.py")
            break
        elif choice == '4':
            os.system("python Salmentakmenu.py")
            break
        elif choice == '5':
            print("\nEgun ona izan!")
            break
        else:
            print("\nAukera baliogabea. Mesedez, sartu 1 eta 5 arteko zenbaki bat.")

if __name__ == "__main__":
    main()
