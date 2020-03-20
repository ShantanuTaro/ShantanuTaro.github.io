import requests
response = requests.get("https://corona.lmao.ninja/all")
a = response.json()
print("Total CASES in world: ",a["cases"])
print("Total DEATHS in world: ",a["deaths"])
print("Total RECOVERED in world: ",a["recovered"])