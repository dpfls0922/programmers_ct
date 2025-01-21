import numpy as np
import matplotlib.pyplot as plt
import networkx as nx

# 지뢰찾기 맵 예시
map_example = [
    ['.', '.', '*'],
    ['.', '.', '*'],
    ['*', '*', '.']
]

# 맵 크기
N = len(map_example)

# 방향 (상, 하, 좌, 우, 대각선)
directions = [(-1, 0), (1, 0), (0, -1), (0, 1), (-1, -1), (-1, 1), (1, -1), (1, 1)]

# DFS 탐색 함수
def dfs(x, y, visited, graph):
    visited[x][y] = True
    for dx, dy in directions:
        nx, ny = x + dx, y + dy
        if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny] and map_example[nx][ny] == '.':
            graph.add_edge((x, y), (nx, ny))  # 그래프에 간선 추가
            dfs(nx, ny, visited, graph)

# BFS 탐색 함수
def bfs(x, y, visited, graph):
    queue = [(x, y)]
    visited[x][y] = True
    while queue:
        cx, cy = queue.pop(0)
        for dx, dy in directions:
            nx, ny = cx + dx, cy + dy
            if 0 <= nx < N and 0 <= ny < N and not visited[nx][ny] and map_example[nx][ny] == '.':
                visited[nx][ny] = True
                queue.append((nx, ny))
                graph.add_edge((cx, cy), (nx, ny))  # 그래프에 간선 추가

# 그래프 초기화
graph_dfs = nx.Graph()
graph_bfs = nx.Graph()

# DFS 탐색
visited_dfs = np.zeros((N, N), dtype=bool)
dfs(0, 0, visited_dfs, graph_dfs)

# BFS 탐색
visited_bfs = np.zeros((N, N), dtype=bool)
bfs(0, 0, visited_bfs, graph_bfs)

# 그래프 시각화
plt.figure(figsize=(12, 6))

# DFS 그래프 시각화
plt.subplot(1, 2, 1)
nx.draw(graph_dfs, with_labels=True, node_size=500, node_color='skyblue', font_size=12)
plt.title("DFS 탐색")

# BFS 그래프 시각화
plt.subplot(1, 2, 2)
nx.draw(graph_bfs, with_labels=True, node_size=500, node_color='lightgreen', font_size=12)
plt.title("BFS 탐색")

plt.show()
