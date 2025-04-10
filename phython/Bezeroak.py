class Bezeroak():
    def __init__(self, ida, izena, email, telefono,herri,postakode,direkzio,erregistro):
        self.IDAK = ida
        self.IZENAK = izena
        self.EMAILAK = email
        self.TELEFONOA = telefono
        self.HERRIA = herri
        self.POSTAKODEA = postakode
        self.DIREKZIOA = direkzio
        self.ERREGISTROA = erregistro

    def setAtributte(self):
        self.IDAK = input("Id sartu: ")
        self.IZENAK = input("Izena sartu: ")
        self.EMAILAK = input("Emaila sartu: ")
        self.TELEFONOA = input("Telefonoa sartu: ")
        self.HERRIA = input("Herria sartu: ")
        self.POSTAKODEA = input("Posta Kodea sartu: ")
        self.DIREKZIOA = input("Direkzioa sartu: ")
        self.ERREGISTROA = input("Erregistroa sartu: ")

    def __setid(self):
        self.IDAK = input("ida definitu: ")

    def __setizena(self):
        self.IZENAK = input("izena definitu: ")

    def __setemaila(self):
        self.EMAILAK = input("emaila definitu: ")

    def __settelefonoa(self):
        self.TELEFONOA = input("telefonoa definitu: ")

    def __setherria(self):
        self.HERRIA = input("herria definitu: ")

    def __setkodea(self):
        self.POSTAKODEA = input("posta kodea definitu: ")

    def __setdirekzioa(self):
        self.DIREKZIOA = input("direkzioa definitu: ")

    def __seterregistroa(self):
        self.ERREGISTROA = input("erregistroa definitu: ")


    def __getid(self):
        return self.IDAK

    def __getizena(self):
        return self.IZENAK

    def __getemaila(self):
        return self.EMAILAK

    def __gettelefonoa(self):
        return self.TELEFONOA

    def __getherria(self):
        return self.HERRIA

    def __getkodea(self):
        return self.POSTAKODEA

    def __getdirekzioa(self):
        return self.DIREKZIOA

    def __geterregistroa(self):
        return self.ERREGISTROA

    def print(self):
        print(self.IDAK, self.IZENAK, self.EMAILAK, self.TELEFONOA, self.HERRIA, self.POSTAKODEA, self.DIREKZIOA, self.ERREGISTROA, end=' ')