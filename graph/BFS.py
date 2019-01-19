# search (node) algorithm
def person_is_seller(name):
      return name[-1] == 'm'

graph = {}
graph["you"] = ["alice", "bob", "claire"]
graph["bob"] = ["anuj", "peggy"]
graph["alice"] = ["peggy"]
graph["claire"] = ["thom", "jonny"]
graph["anuj"] = []
graph["peggy"] = []
graph["thom"] = []
graph["jonny"] = []

def search(name):
    search_queue = [] # 创建一个队列
    search_queue += graph[name] # 将你的邻居都加入到这个搜索队列中
    searched = [] # 该数组用于记录检查过的人
    while search_queue: # 只要队列不为空
        target = search_queue.pop() # 就取出其中的第一个人
        if not target in searched: # 仅当这个人没检查过时才检查
            if person_is_seller(target): # 检查这个人是否是芒果销售商
                print (target + " is a mango seller!") # 是芒果销售商
                return True
            else:
                search_queue += graph[target] # 不是芒果销售商。将这个人的朋友都加入搜索队列
                searched.append(target) # 将这个人标记为检查过
    return False # 队列中没人是芒果销售商
print(graph)

search("you")