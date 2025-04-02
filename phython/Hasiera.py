import datetime
import os

def show_menu():
    print("\n ========= HASIERAKO MENUA ========= ")
    now = datetime.datetime.now()
    print("\nFecha y hora actuales:", now.strftime("%Y-%m-%d %H:%M:%S"))
    print("1.  PRODUKTOAK")
    print("2. LANGILEAK")
    print("3.  ERABILTZAILEAK")
    print("4.  SALMENTAK")
    print("5.  IRTEN")

def main():
    while True:
        show_menu()
        choice = input("\nSeleccione una opción (1-5): ")

        if choice == '1':
            print("Funcionalidad para ERABILTZAILEAK aún no implementada.")
        elif choice == '2':
            print("Funcionalidad para LANGILEAK aún no implementada.")
        elif choice == '3':
            os.system("python menu.py")
        elif choice == '4':
            print("Funcionalidad para SALMENTAK aún no implementada.")
        elif choice == '5':
            print("\nTenga un buen día!")
            break
        else:
            print("\nOpción inválida. Por favor, introduce un número entre 1 y 5.")

if __name__ == "__main__":
    main()
