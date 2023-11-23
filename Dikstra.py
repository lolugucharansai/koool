import heapq

def dijkstra(graph, start):
    distances = {node: float('infinity') for node in graph}
    distances[start] = 0
    queue = [(0, start)]

    while queue:
        current_distance, current_node = heapq.heappop(queue)

        if current_distance > distances[current_node]:
            continue

        for neighbor, weight in graph[current_node].items():
            distance = current_distance + weight

            if distance < distances[neighbor]:
                distances[neighbor] = distance
                heapq.heappush(queue, (distance, neighbor))

    return distances
def network_routing(graph, start, end):
    distances = dijkstra(graph, start)
    return distances[end]

def shortest_path(graph, start, end, avoid_nodes):
    # Remove the nodes to avoid from the graph
    graph = {node: edges for node, edges in graph.items() if node not in avoid_nodes}
    
    return network_routing(graph, start, end)


def get_user_input():
    num_nodes = int(input("Enter the number of nodes: "))
    graph = {}
    for i in range(num_nodes):
        node = input("Enter node name: ")
        graph[node] = {}
        num_neighbors = int(input("Enter the number of neighbors for {}: ".format(node)))
        for j in range(num_neighbors):
            neighbor, weight = input("Enter neighbor and weight (separated by a space): ").split()
            graph[node][neighbor] = int(weight)
    start_node = input("Enter the start node: ")
    return graph, start_node

def main():
    graph, start_node = get_user_input()
    print(dijkstra(graph, start_node))

if __name__ == "__main__":
    main()
