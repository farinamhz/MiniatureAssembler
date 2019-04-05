import sys

def readfile(fileadress):
    with open(fileadress, 'r') as filetemp:
        file = filetemp.readlines()
        return file

def label_space(str):
    if ' ' in str or '\t' in str:
        return True
    return False

def j_label(str):
    for i in range(len(str)):
        if str[i] == ' ' or str[i] == '\t':
            for j in range(i,len(str)):
                if str[j] != ' ' and str[j] != '\t':
                    print('two labels for one opcode in line:')
                    return True
            return False

def campare(str):
    if not str.isdigit():
        print("wrong register adress")
        return False
    i = int(str)
    if i >= 16 or i < 0 :
        print("wrong register adress")
        return False
    return True

def cama(str):
    templist = []
    o = 0
    if str[len(str)-1] == ' ' or str[len(str)-1] == '\t':
        for i in range(len(str)-1,-1,-1):
            if str[i] != ' ' and str[i] != '\t':
                str = str[:i+1]
                break
    for i in range(len(str)):
        if str[i] == ",":
            templist.append(str[o:i])
            o = i + 1
        elif str[i] == ' ' or str[i] == '\t':
            templist = []
            print("cant have space or tab between numbers:")
            return templist
    templist.append(str[o:])
    if len(templist) != 3 :
        print("there should be 3 register numbers")
        templist = []
    return templist

def cama2(str):
    templist = []
    o = 0
    if str[len(str)-1] == ' ' or str[len(str)-1] == '\t':
        for i in range(len(str)-1,-1,-1):
            if str[i] != ' ' and str[i] != '\t':
                str = str[:i+1]
                break
    for i in range(len(str)):
        if str[i] == ",":
            templist.append(str[o:i])
            o = i + 1
        elif str[i] == ' ' or str[i] == '\t':
            templist = []
            print("cant have space or tab between numbers:",end=' ')
            return templist
    templist.append(str[o:])
    if len(templist) != 2 :
        print("there should be 2 register numbers")
        templist = []
    return templist

class instruction:
    def __init__(self):
        self.r_list = ["add", "sub", "slt", "or", "nand"]
        self.l_list = ["addi", "ori", "slti", "lui", "lw", "sw", "beq", "jalr"]
        self.lo_list = ["lw", "sw", "beq"]
        self.li_list = ["addi", "ori", "slti"]
        self.lii_list = ['lui', 'jalr']
        self.j_list = ["j", "halt"]
        self.d_list = [".fill", ".space"]

    class r_type:

        def check(self , str):
            t_list = []
            ctemp = cama(str)
            if len(ctemp) != 0:
                t_list = ctemp
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
            if len(t_list) == 0:
                return False
            for i in t_list:
                result = campare(i)
                if not result:
                    return False
            return True

        def check_off(self, str):
            t_list = cama(str)
            if len(t_list) == 0:
                return False
            for i in range(len(t_list)-1):
                result = campare(t_list[i])
                if not result:
                    return False
            if label_space(t_list[2]):
                return False
            return True

        def check_imm(self, str):
            t_list = cama(str)
            if len(t_list) == 0:
                return False
            for i in range(len(t_list)-1):
                result = campare(t_list[i])
                if not result:
                    return False
            return True

        def check_im(self, str, o):
            if o == 1:
                t_list = cama2(str)
                if len(t_list) == 0:
                    return False
                for i in range(len(t_list)):
                    result = campare(t_list[i])
                    if not result:
                        return False
                return True
            if o == 2:
                t_list = cama2(str)
                if len(t_list) == 0:
                    return False
                for i in range(len(t_list)-1):
                    result = campare(t_list[i])
                    if not result:
                        return False
                if label_space(t_list[1]):
                    return False
                return True

def inside(str):
    f = instruction()
    if str in f.r_list or str in f.l_list or str in f.j_list or str in f.d_list:
        return True
    return False

def removing_label(str):
    if str == 'halt':
        return str
    elif str[0] != ' ' and str[0] != '\t':
        for i in range(len(str)):
            if str[i] == ' ' or str[i] == '\t':
                first = str[:i]
                if inside(first):
                    return str
                elif label_space(first):
                    temp = ''
                    return temp
                else:
                    for j in range(i,len(str)):
                        if str[j] != ' ' and str[j] != '\t':
                            temp = str[j:]
                            return temp
    else:
        for i in range(len(str)):
            if str[i] == ' ' or str[i] == '\t':
                temp = str[i+1:]
                return temp

def getting_opcode(str):
    if str == "halt":
        return str
    for i in range(len(str)):
        if str[i] == " " or str[i] == '\t' and i <= 6:
            return str[0:i]
    print("invalid opcode", end=' ')
    return ''

def getting_code(str):
    for i in range(len(str)):
        if str[i] == " " or str[i] == '\t':
            for j in range(i,len(str)):
                if str[j] != ' ' and str[j] != '\t':
                    return str[j:]
            print("there is no register adress in line")
            return ''
    return ''

def removing_hashtag(str):
    if str == None :
        str = ' '
        return str
    for i in range(len(str)):
        if str[i] == '#':
            for j in range(i-1,0,-1):
                if str[j] != ' ' and str[j] != '\t':
                    return str[:j+1]
            return str[:i]

    return str

def removing_space(str):
    temp = []
    for i in str:
        if i != '\n':
            temp.append(i)
    return temp

def removing_n(str):
    if str[0] == ' ' or str[0] == '\t':
        for i in range(len(str)):
            if str[i] != ' ' and str[i] != '\t':
                str = str[i:]
                break
    if str[len(str)-1] == ' ' or str[len(str)-1] == '\t' or str[len(str)-1] == '\n':
        for i in range(len(str)-1,0,-1):
            if str[i] != ' ' and str[i] != '\t' and str[i] != '\n':
                str = str[:i+1]
                break
    ns = ''
    for i in range(len(str)-1):
        ns = ns + str[i]
    return str

inst = instruction()

inp = sys.argv
txtfile_add = inp[1]
txtfile = readfile(txtfile_add)
txtfile = removing_space(txtfile)
n_line = 0
final_result = True

for line in txtfile:
    n_line = n_line + 1
    line = removing_n(line)
    orginal_line = line[:]
    line = removing_label(line)
    line = removing_hashtag(line)
    if len(line) == 0:
        print("two labels for one line :")
        print(orginal_line)
        final_result = False
        break
    opcode = getting_opcode(line)
    code = getting_code(line)

    if opcode != 'halt':
        if len(opcode) == 0 or len(code) == 0:
            print('dont have code or opcode :')
            print(orginal_line)
            final_result =False
            break

    if opcode in inst.r_list:

        instin = inst.r_type()
        if not instin.check(code):
            print(orginal_line)
            final_result = False
            break

    elif opcode in inst.l_list:
        instin = inst.l_type()
        if opcode in inst.lo_list:
            if not instin.check_off(code):
                print(orginal_line)
                final_result = False
                break
        elif opcode in inst.li_list:
            if not instin.check_imm(code):
                print(orginal_line)
                final_result = False
                break
        elif opcode in inst.lii_list:
            if opcode == 'jalr':
                if not instin.check_im(code,1):
                    print(orginal_line)
                    final_result = False
            elif opcode == 'lui':
                if not instin.check_im(code,2):
                    print(orginal_line)
                    final_result = False
        else:
            if not instin.check(code):
                print(orginal_line)
                final_result = False
                break

    elif opcode in inst.j_list:
        if opcode == "j":
            if j_label(code):
                print(orginal_line)
                final_result = False
                break
        elif opcode == "halt":
            if code != '':
                final_result = False
                break

    elif opcode in inst.d_list:
        if label_space(code):
            final_result = False
            break

    else:
        print("wrong opcode")
        print(orginal_line)
        final_result = False
        break

if final_result:
    print("ok")