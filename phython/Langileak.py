class Langileak():
    def __init__(self, ida, izena, kargua, email, telefonoa, herria, kodea, elbide,altdata):
        self.IDAK = ida
        self.IZENAK = izena
        self.KARGUAK = kargua
        self.EMAILAK = email
        self.TELEFONOAK = telefonoa
        self.HERRIAK = herria
        self.POSTAKODEAK = kodea
        self.ELBIDEAK = elbide
        self.ALTDATAK = altdata

    def setAtributte(self):
        self.IDAK = input("Id sartu: ")
        self.IZENAK = input("Izena sartu: ")
        self.KARGUAK = input("Emaila sartu: ")
        self.EMAILAK = input("Telefonoa sartu: ")
        self.TELEFONOAK = input("Herria sartu: ")
        self.HERRIAK = input("Posta Kodea sartu: ")
        self.POSTAKODEAK = input("Direkzioa sartu: ")
        self.ELBIDEAK = input("Erregistroa sartu: ")
        self.ALTDATAK = input("Alternative Data sartu: ")

    def __setid(self):
        self.IDAK = input("ida definitu: ")

    def __setizena(self):
        self.IZENAK = input("izena definitu: ")

    def __setemaila(self):
        self.KARGUAK = input("emaila definitu: ")

    def __settelefonoa(self):
        self.EMAILAK = input("telefonoa definitu: ")

    def __setherria(self):
        self.TELEFONOAK = input("herria definitu: ")

    def __setkodea(self):
        self.HERRIAK = input("posta kodea definitu: ")

    def __setdirekzioa(self):
        self.POSTAKODEAK = input("direkzioa definitu: ")

    def __seterregistroa(self):
        self.ELBIDEAK = input("erregistroa definitu: ")

    def __setaltdata(self):
        self.ALTDATAK = input("Alternative Data definitu: ")


    def __getid(self):
        return self.IDAK

    def __getizena(self):
        return self.IZENAK

    def __getemaila(self):
        return self.KARGUAK

    def __gettelefonoa(self):
        return self.EMAILAK

    def __getherria(self):
        return self.TELEFONOAK

    def __getkodea(self):
        return self.HERRIAK

    def __getdirekzioa(self):
        return self.POSTAKODEAK

    def __geterregistroa(self):
        return self.ELBIDEAK

    def __getaltdata(self):
        return self.ALTDATAK

    def print(self):
        print(self.IDAK, self.IZENAK, self.KARGUAK, self.EMAILAK, self.TELEFONOAK, self.HERRIAK, self.POSTAKODEAK, self.ELBIDEAK,self.ALTDATAK, end=' ')