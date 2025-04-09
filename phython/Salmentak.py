class Salmentak():
    def __init__(self, idsalmenta, idproduktua, idbezeroa, data, saltzailea):
        self.IDSALMENTAK = idsalmenta
        self.IDPRODUKTUA = idproduktua
        self.IDBEZEROA = idbezeroa
        self.DATA = data
        self.SALTZAILEA = saltzailea

    def getIda(self):
        return self.IDSALMENTAK


    def setAtributte(self):
        self.IDSALMENTAK = input("Salmentaren Ida sartu: ")
        self.IDPRODUKTUA = input("Produktuaren Ida sartu: ")
        self.IDBEZEROA = input("Bezeroaren Ida sartu: ")
        self.DATA = input("Data sartu: ")
        self.SALTZAILEA = input("Saltzailea sartu: ")


    def __setidsalmenta(self):
        self.IDSALMENTAK = input("Salmentaren Ida definitu: ")

    def __setidproduktua(self):
        self.IDPRODUKTUA = input("Produktuaren Ida definitu: ")

    def __setidbezeroa(self):
        self.IDBEZEROA = input("Bezeroaren Ida definitu: ")

    def __setdata(self):
        self.DATA = input("Data definitu: ")

    def __setsaltzailea(self):
        self.SALTZAILEA = input("Saltzailea definitu: ")

    def __getidsalmenta(self):
        return self.IDSALMENTAK

    def __getidproduktua(self):
        return self.IDPRODUKTUA

    def __getidbezeroa(self):
        return self.IDBEZEROA

    def __getdata(self):
        return self.DATA

    def __getsaltzailea(self):
        return self.SALTZAILEA

    def print(self):
        print(self.IDSALMENTAK, self.IDPRODUKTUA, self.IDBEZEROA, self.DATA, self.SALTZAILEA, end=' ')