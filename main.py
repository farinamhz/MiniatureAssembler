def readfile(fileadress):
    with open(fileadress, 'r') as filetemp:
        file = filetemp.readlines()
        return file

def campare(str):
    if not str.isdigit():
        print("wrong register adress", end=' ')
        return False
    i = int(str)
    if i >= 16 or i < 0 :
        print("wrong register adress", end=' ')
        return False
    return True

def cama(str):
    templist = []
    o = 0
    for i in range(len(str)):
        if str[i] == "," :
            templist.append(str[o:i])
            o = i + 1
    templist.append(str[o:])
    if len(templist) != 3 :
        print("wrong code" , end=' ')
        templist = []
    return templist

class instruction:
    def __init__(self):
        self.r_list = ["add", "sub", "slt", "or", "nand"]
        self.l_list = ["addi", "ori", "slti", "lui", "lw", "sw", "beq", "jalr"]
        self.lo_list = ["lw", "sw", "beq"]
        self.li_list = ["addi", "ori", "slti", "lui"]
        self.j_list = ["j", "halt\t"]
        self.d_list = [".fill", ".space"]

    class r_type:

        def check(self , str):
            t_list = []
            if len(cama(str)) != 0:
                t_list = cama(str)
                for i in t_list:
                    result = campare(i)
                    if not result:
                        return False
                return True
            return False

    class l_type:
        def check(self , str):
            t_list = []
            t_list = cama(str)
            for i in t_list:
                result = campare(i)
                if not result:
                    return False
            return True

        def check_off(self, str):
            t_list = cama(str)
            for i in range(len(t_list)-1):
                result = campare(t_list[i])
                if not result:
                    return False
            return True

        def check_imm(self, str):
            t_list = cama(str)
            for i in range(len(t_list)-1):
                result = campare(t_list[i])
                if not result:
                    return False
            if not t_list[2].isdigit():
                return False
            return True


def removing_title(str):
    temp = ''
    if str[0] == '\t' or str[0] == ' ':
        temp = str[1:]
    else:
        for i in range(len(str)):
            if str[i] == ' ' or str[i] == '\t':
                temp = str[i+1:]
                break
    return temp

def getting_opcode(str):
    if str == "halt\t":
        return str
    for i in range(len(str)):
        if str[i] == " " and i <= 6:
            return str[0:i]
    print("invalid code", end=' ')
    return False

def getting_code(str):
    for i in range(len(str)):
        if str[i] == " ":
            return str[i+1:]

def removing_hashtag(str):
    temp = str
    for i in range(len(str)):
        if str[i] == '#':
            if str[i-1] == '\t' or str[i-1] == ' ':
                temp = str[:i-1]
            else:
                temp = str[:i]
            break
    return temp

def removing_space(str):
    temp = []
    for i in str:
        if i != '\n':
            temp.append(i)
    return temp

a = instruction()

txtfile_add = input()
txtfile = readfile(txtfile_add)
txtfile = removing_space(txtfile)
n_line = 0
final_result = True

for line in txtfile:
    n_line = n_line + 1
    line = removing_title(line)
    line = removing_hashtag(line)
    opcode = getting_opcode(line)
    code = getting_code(line)

    if opcode in a.r_list:

        aa = a.r_type()
        if not aa.check(code):
            print("in line",n_line)
            final_result = False
            break

    elif opcode in a.l_list:

        aa = a.l_type()
        if opcode in a.lo_list:
            if not aa.check_off(code):
                print("in line", n_line)
                final_result = False
                break
        elif opcode in a.li_list:
            if not aa.check_imm(code):
                print("in line", n_line)
                final_result = False
                break
        else:
            if not aa.check(code):
                print("in line", n_line)
                final_result = False
                break

    elif opcode in a.j_list:

        if opcode == "j":
            continue
        elif opcode == "halt":
            if code != None:
                final_result = False
                break

    elif opcode in a.d_list:
        continue

    else:
        print("in line" , n_line)
        final_result = False
        break

if final_result:
    print("ok")