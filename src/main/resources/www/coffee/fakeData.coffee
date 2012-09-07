fake ={
  nodes: [
    ['a','i am a'],
    ['b','B, really B'],
    ['c','我是一枚汉字哦'],
    ['d','d'],
    ['e','e, lalala']
  ]
  edges: [
    ['a','b'],
    ['a','c'],
    ['b','e'],
    ['d','a'],
    ['d','e']
  ]
}

fackDomain = [new Resource('resourceA',[new Tag('A','TagA'),new Tag('B','TagB')]),
new Resource('resourceB',[new Tag('A','TagA'),new Tag('C','TagC')]),
new Resource('resourceC',[new Tag('D','TagD'),new Tag('E','TagE')]),
new Resource('resourceD',[new Tag('A','TagA'),new Tag('E','TagE'),new Tag('B','TagB')])]

