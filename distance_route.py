class DVR:
    def __init__(self):
        self.graph = []
        self.via = []
        self.rt = []
        self.v = 0
        self.e = 0

    def main(self):
        print("Please enter the number of Vertices: ")
        self.v = int(input())

        print("Please enter the number of Edges: ")
        self.e = int(input())

        self.graph = [[9999 if i != j else 0 for j in range(self.v)] for i in range(self.v)]
        self.via = [[100 for _ in range(self.v)] for _ in range(self.v)]
        self.rt = [[9999 for _ in range(self.v)] for _ in range(self.v)]

        for i in range(self.e):
            print(f"Please enter data for Edge {i + 1}:")
            s = int(input("Source: ")) - 1
            d = int(input("Destination: ")) - 1
            c = int(input("Cost: "))
            self.graph[s][d] = c
            self.graph[d][s] = c

        self.dvr_calc_disp("The initial Routing Tables are: ")

        s = int(input("Please enter the Source Node for the edge whose cost has changed: ")) - 1
        d = int(input("Please enter the Destination Node for the edge whose cost has changed: ")) - 1
        c = int(input("Please enter the new cost: "))
        self.graph[s][d] = c
        self.graph[d][s] = c

        self.dvr_calc_disp("The new Routing Tables are: ")

    def dvr_calc_disp(self, message):
        print()
        self.init_tables()
        self.update_tables()
        print(message)
        self.print_tables()
        print()

    def update_table(self, source):
        for i in range(self.v):
            if self.graph[source][i] != 9999:
                dist = self.graph[source][i]
                for j in range(self.v):
                    inter_dist = self.rt[i][j]
                    if self.via[i][j] == source:
                        inter_dist = 9999
                    if dist + inter_dist < self.rt[source][j]:
                        self.rt[source][j] = dist + inter_dist
                        self.via[source][j] = i

    def update_tables(self):
        k = 0
        for i in range(4 * self.v):
            self.update_table(k)
            k += 1
            if k == self.v:
                k = 0

    def init_tables(self):
        for i in range(self.v):
            for j in range(self.v):
                if i == j:
                    self.rt[i][j] = 0
                    self.via[i][j] = i
                else:
                    self.rt[i][j] = 9999
                    self.via[i][j] = 100

    def print_tables(self):
        for i in range(self.v):
            for j in range(self.v):
                print(f"Dist: {self.rt[i][j]:<5}", end=" ")
            print()


if __name__ == "__main__":
    dvr_instance = DVR()
    dvr_instance.main()
