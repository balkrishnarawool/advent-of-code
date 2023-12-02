package aoc2023;

public class Problem01Input {
    static final String INPUT01 = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
            """;

    static final String INPUT02 = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
            """;

    static final String INPUT03 = """
            fivepqxlpninevh2xxsnsgg63pbvdnqptmg
            eight8zlctbmsixhrvbpjb84nnmlcqkzrsix
            hkxqfrqmsixfplbkpkdfzzszjxmdjtdkjlprrvr3gghlrqckqtbng
            zkjkctxvssix1dqb22five
            4dtlmkfnm
            four539tkqrc
            blxqb7onetvmfjlvglrnbtdonegfourfour
            lqzrclnlzrvdstgtoneseven1xrvdchn29
            tczmtfkqhthreetwo7five
            kncvqpzdtfs7
            6seveneighttwonine2
            sixbfgxndseven9nvzr6ftsqb1
            ddgjgcrssevensix37twooneightgt
            zclvhfz91zbdkrreightbzqttdxrone
            five18twofiveclqqsrsfdbrt
            ninedflcfblvjhr3
            32eightnmlv5lgbckz
            nine7xqz81dtpld
            2tmbddjl9cgcrvnrpgl
            5twofivedlk1pfjjmctjh
            nvcchgjnine9sixtwompfrp
            685
            54two7twobsfpkxjninefoureight
            fgsvpxcx4zzxfqdkssixgbssrqmnpz9threethreefour
            three3sixnine38bprqqkpdr6seven
            xgmc3sixthree1
            3eight2twojqsvbtftp
            six7sixtwos1mhzfpzmhfcslrmfive
            bmxmxzldtngrgbt538jzqvjlrrc4
            sevenxqzcrgzvfiveggxlxf4fiveppvrxjkdk9
            one8six
            fourdlhgvx1onesix
            onelpbxfnjm28gjjs5
            pdb69six6cdxmxxbfour5five
            eightjndqhjzv43five631
            9fourldr92eight
            three1sevensrxzlnxrnine7four
            eightfive4bldzgtpvslgkrmlmkftpone
            22357fourone6
            sixrzhgvzsjsix9dbldsevenfivedtnbkjjxfc
            mcgfpfh1three
            ninetwo1
            sqgtvmcvbfsslsdnine19three1two
            f5nine7six
            1sbggj
            5eight56342h
            3onezdfsvngvjg4fqeight
            sixncfrkbqthreeqsbpvspjt4
            fjseventwo4k
            five8five
            4oneone14
            prpnzmnsjsfivetdrqpt54
            2threefour2fourrkkndqzq
            lreightfourfour12sfjkjmbntkgfjv
            sixfour93
            eightfivetwo1one
            three5eightjk5twothreeseven
            tz62twofivetclnxfp8dhsxzgpxhsfhx
            fivefivecrjhmbrk6three
            58fiveseven3five
            nine4six6hqmqqqbktkfvmb
            7five8k
            5kbfjmqppvrbjdkc
            hkpchj2gknjhvc
            7twosix
            2kltwo
            six6vlmhdmvtdsix
            xqtpnglkktvpktfive1
            9one91
            vbrcfmjeight4qmtzmpqhsninejdmvzpdtpq2
            3eightzdcrvqeightl7nsbpdrczdrnine
            nine6hkfeight9
            73pqqqsixone7bvl
            1threeeight9six
            46one9smxbqzrppr56six
            njoneight27fourthreevtxf51
            fivekbdfkfjk2
            two1lxdpqtq2xhffmbcbll1
            tlngnjqlkmcx44mnhpbg9threepngzm2
            nine4qgccpx1nine
            four8515svqscfsds
            5fllxvlhqcp618four6kkone
            nsxbrczm566cldngf4qncqmcvvrgztxrr
            1seveneighttbrlnfbdkzkppnineqppcdvnpthree
            fiveninesevenfbsz1crjpbncrh
            12jsdmvonesth
            4ngrc5
            ffpknzsjcgrdxfxjrneights3five
            66nfcsztrtmdf
            5three5twojmsxvpczpd5
            pb1fournjgkfcbn2shzgpnfx
            rzphlccmdseven7fxfiveone
            2foureight25
            n4jdlrjkfppfive4phvcfnineeight
            fivedfplsxxmjsmsrxbrbv3sixsix
            pnbpqfxsdhlkklmk4seventhree2
            8seven7sixgplpldf9
            zx13cxmckxfbmsxlssrttwo
            15sfs
            hktnxtwothree3pqzzkfdrhjmhmk6onexnhnvhx
            13vz1bgbbgcljq8d
            hrtfeight822
            5threefourjdmrnhrvmkfiveeight9seven4
            six13zxtcms
            6966
            xcslbzkmv3pmlrnnn471nine
            nzmslhzlh5zdnfone39cp2
            9sixfourtwothreefivehnxtgnxxbpdmxx
            twoqc4lrpgtwo
            seven2threeeightwomg
            nzsdjhseven97two93xmprjfkrbnbdpvl
            pgdszjfive6
            sevenhpbnxxntt25fzfrpbndnkmftsbhpjntdxpvcg
            nnxktphmqqhxnmfrgqnqqgqtfqqvphn36sixsppns
            fourxcp2six9
            bz5five2onejttjqmhml7
            lrg2ksxhvrfive1twosevenseven5
            1jkrllhnmqgvnbvseven
            kczvjfzrqh68sevenonethree
            onehqvp3
            ninecczpd7
            fourvlmmhlfrvf4onekpbs
            fivezqkcctwoplmrkksfone93three
            fhvhfq1tvvt3ckfgtwofour
            649twomktwonebx
            5fiverbscnmbnnsixfnnlnqq3
            3tsjfive
            7eight4zsxjftmlbjfourqsgbzdtxfgxbn
            mbklhngfthree5
            94qqrpvd6
            gvkrsxs3
            two5c6
            51eight5xkgpsxxz
            5fjvr
            fiveonenine38
            one68
            7two15cbxmssb25qtcpcjtwonef
            two368vtxkdg7
            fiveqrjneight1fourfour
            six4one9twonetm
            msjptzgvbmseven8
            4xrklnqqf2sxxt
            hsmzcbcgcfivenines88fkf5
            1sixeight6onexsdh28
            8sevenc267
            svlmp99jvbrzh
            qvnpsfvvfiveeightpmdpv4
            seven6twothreethree
            2rgd2
            3dsrcdkzndthreexfsldcthreetwo
            sixn575pzpmmlxvr41five
            qmchsxd6qcmmcjh9q
            dqvjfourmqp1six
            sbglrkhrhrfldkftzfknblj1twonemm
            ninehsgdt38onedhbjzs
            5rm
            five2mphbljl
            kmgmnfddnine1one3three3
            ninefour97
            7twogmhdmnxfrgnsnxr5fourlone
            xnqcdjdsixone463
            49xdzq
            8qrjgtwo5seventwo2vdz
            1xxjvqtrfnfive3
            threebfmprszf5dbnmmlggthreepkf797
            eightnine645two1fivetknzvmffs
            tbjgzkqrzgqseven5nfvqpcqmgsbjfive
            1beight5zdvjpcnzxsmseven
            7rvmfbjs9krfkhthnkkv
            eighttgl21
            63onesjfpjgnrg42lbcnccqfvssix
            cqxsrzcnine7jgsdssixkmtfljnpstwo3
            43qzzklffxl23
            1sixsjxmnqfourmbghd
            p8njsdrmtzlsixsix7kpbkfq5
            sevensixvvqcqrcsix7jtwo
            vbgtmkdmjdjlcnine41twotwoksrtkone
            fourcnstnbgfive7twof76
            ninesixtqsmcrseven28nblqqnjgx
            8threeeightcrqm5
            xrzgqksx5lbfjmhbnsg3fx8
            sevenpnng2xcktsvccpc81eight
            673
            twofivehqsmrcbt1
            6gjmlspgfsmfdvnlpktxpfkprjxxzseven
            ninettdbgqznninenine22mmjlstcjbbzone
            fourfvdmvsvtwo43vhpmfcqrpfxznkhr
            4two96fournineqxckmkseven
            lteightwo4
            38fivenine678
            5nqkfm
            cptwone3zlggrkrfkgtwonine4rcpeightfourhxdvtmvqz
            ninetwofivefivetwosix29six
            one86
            6xjxzjdskksix2one
            vjssjmqlkzzqfzht5one5leight1four
            75sevenfour5
            sixthree4eight
            sixsixfourspxsvpckcrfr8oneptbdvsfh
            54qkgpzqhdr8fqvhj3xpmf65
            nqoneight5eight3lcxftzxdqdhftlvkpbgpnine
            9dkzvd6
            4jhnvmr43one9eight
            8bmgfnx
            fivesix9xxzjrdkzhb
            92h
            vlcthreeninetmpbj47vr
            grmkzfive6fourdk64
            one4six
            three3dldnpsctjcbcxrs
            5eightsevenjtwonem
            fourthreeonelncnqlhvms4eight
            8one8six1
            7ninegmzsninethree89nq
            ninelcsgone11seventhreedzxnpzbnine
            4rtnltdfour55kfzl
            lnine1threefplpkbjmseven
            onetmztjjrksix5fgvxrdfnrcfbfour
            ninevtvrrqtrbpxrbqqfsrzglqbdftmz6
            kldqgeight9mvfrdl
            onejcndvflhfz939519
            6btbfdsgzrrzthreexdsix
            pdrzkpcbz8four4two
            4fglf7rssfive
            fiveeight8nhfkeightsix
            599
            threethreelhb8
            sixn7nineonetwottp
            one2zqtmbzdqbcdone
            8hfleighteightczrsix5three
            eight2nineone
            2onekkshvstpsix5gddngmcgjr
            pnjhrqp212pn3
            mpq8fiveseven91clpcshhvrh3
            lnkvnnnfddzhfzfpb4htclkp2three5hl
            jlmqgdbq7seveneightzc132
            19lnfourdrfhsfgqjc
            rrcsxzmkt4fivesixseven2fivetwo
            8cvfnbtq7
            pdrqpsnzbjllknstfgkc88
            hpchslzcjskmqfsszrphthrdsgveight2sevensix
            fiveninescglgcnxthreeseven6nine
            4sixqzvzq
            55six5fourfour
            xfd1gmpqrjrdeightxfdtldhtgcgj
            lchsxqtgdeighttztntqm4
            qhgmtjq95ninetwo8seven
            8oneeight6eightsix3
            7threenine4onedhnthree
            8mgkjzvlghf3eightonehhxxn1
            eighttwo73threetwodx8
            2167
            eight7eightcx
            fxmntxxltwoninesix8five5
            9nmmseven4seven9
            ninesgt2dcssdprbstgrrgfmtwofive
            6vtg
            fourpnmkckmdx45qztcgbbqdqrnine
            cbztwosixsjlzjdgrppmk34four2
            llhtrzgqxc47five1eight
            bhdf315nineoneightzlp
            nktvxnllj15six4ninevnffgfq
            8fivethreeone794onetwo
            gf3bknqbtn1fivexgppseven
            16qlgrrjthreegps9
            two6three
            four7rxsxnvstg9eightthreenine
            onethreevgrhtnpdlvnjklqjqj1
            3kldmncbzthreesevennine
            nine59fnbbnxqcfoursevenfdvmbrs
            62sixone3
            9four6gdxhzone87eightwohl
            4oneskqzhmlz
            ptbcbnftfive213
            7five2one3four5eight
            sevenklqmfhxcqfivellfnlhlkrscxphheightkhpkpqzcl5two
            eightbtdvpxphm6ksixsixpjqhsfbmnz
            6xgvztvssevenseven
            8jjkxdgxtpzvkdzeight
            hflhcmjjkmqn6six
            six979three
            32nine9
            nine5pdxt893
            oneljbrsdvvg99
            foureight3gcxvxxndbonejtqrm
            sixtwofive5nfmg76qnlbjtwo
            21sixeightfive31ngxzh2
            eight7five2lnchgdpqfourdksfhdzmsscnhxksmhbrlgjjsq
            cxreightwonine37dpdkngxhhzvxpxqvfqcxj75
            xjqcv8zcsqhvzxb
            threexgfbjkbdgbpsdg7three7tnqkcjzrh6
            3sixtwohtpdjkdthreeninexvgnmgqsdcrplp
            44twosevenninembfblv5
            mmsm5hh
            seventwosix5nmsllddone97
            gxhbsf4xp6
            foureightonenine769four
            9tmfnfxlgt
            three4oneonenine7tmg
            psp3two
            rfrvvqdrpj3fourxktlcp
            tnzrzsvrcqffdfxr2four
            fourtwosixrnnxsr72
            1onetwo2
            spdshcnsjkpptl4cpsttwo6
            zgjeightwo5five9eightoneeighthzrqzfkrjqlblf
            3sevenchkkxnine7
            qrxg2one24brccpgxrn
            7qgxglktwo
            bmnzkzlrfm683fsdfour
            8one7qgfmrn
            qfxnlfk19ninemjxqzlrbvseventjpgzlfiverpgnv
            dxzkndhfeightdhrmbkbzlseventdltstlbmsix4dddgcsk
            kmhqhhfpg789ljqzxshtwogm11
            569
            5tvcpfx
            nine6chgjjs5zjxqdninexbdcmccmfive
            qzbnineeightfxvxqst6fivexfj4
            five14tqxlnsdlbt77
            three4grd
            1przpcksjqseven7
            sevenninefivetgvc7
            sixdgfmthsevenspcflmg9four
            nineseventwo6eightqjbvbxonefiveone
            twothree8hgbzhnsevenfg3
            sjlmzxktthreefivexndbnpmvnine4
            eightnine3three
            mpnfxcv4nineldxdffggqtscvdzq
            8dfkvcm
            tbvx42l
            7one8cjncqdgpsqkhntmr69
            sevennknhdmvbcpfbzzs719fivebfhgqrjxfc
            1four7threexmpnmcldkktlf
            dsbfq3fiverc5one
            8jghvmlbmqxdsl3seven8nine9
            nineseven2gdzscktwo
            seventwostzmlfive49twopgklvfgpkl2
            739jrlmqqkkmgghzone8seven
            twovzfour9frdgmsftd9
            zqoneightonetwomfbbbfpqkmphbonegsdjnzrf8jkkmxvcqt
            16four8fjtbhjl3
            1nine6hjfcvdxq5dzvjcsevenkbhtgpjclhfive
            4onevtxcttthreeeightdmlzhxqbfive
            szznone1
            6onex2nnb2threeeightwojgf
            nine2549
            teightwo927jxzjtncnqthree6
            dd9
            hzqkthree9
            jfskpztthree4bxprksix
            mjntwonefxmrxnlkeighttwoninerdlnsqcb29
            rsgzxtfnineeightsix5
            threesdxqq4
            472vgjxtknnbmfthree
            kxdfive8vxbtkhz6
            xltrjrpqqpjvtmfive4
            1twofour5954nine7
            lppnine3jrfbhcshfvgvhhfkjnpqv
            cm74seveneightthreesevenseven
            tqszx4eightqhxglflf9tvlpfdconeightq
            hmzeightwotwo1775dssdqkxnfbcqrhfqfqfrrgone
            ninem734snpvccgqq
            onesixdvlvkzqkseven8eightjlr8four
            fh785kddmjsdpns
            sixptfdbssix2fivenineddxlxf
            three8five18
            qsl69
            gggtpfxg4threetwosixone
            7twofourqx
            fourdjg8
            two7one8
            twobftgtppeight8
            7bsprsvnrvlxzst6
            twor934onetwo
            7ddblzjsevenrgntnfvfghxflmpttseven
            fivefourfour2fourseven3
            3hkhgknineonefour
            3fourmppdgkqlcbonesevensevenoneonek
            8two99seven8x9
            four1dfpb3seven
            eight2nine6tzlxz7drdrlzpmxeight
            32nine7
            8ppfgcbp76threefpdrlb
            two3zzrqn8oneone664
            14eightcbftdbveightlksnckmr5four
            6foursevendhrhmcbx
            8sixninethreezcv
            mxzvkmrm43jvhqbrcxjjsnlbbtzdtf2
            189fivejzrd
            sgmhskv8qmfvfhhh3
            nine6jhbvfxsnbhqfnlnrtwo
            five42one3fivesix
            2eight4eightthreecbdzmshjbgnine7
            kt1mdzq2mfour3
            52eight34sbksnnineone
            mzoneight5649chvkzxbnjx
            mr8six1
            fiveqspzvfxsqfhnfmqjzcone3
            81four
            seveneight5twooneightbkq
            onefour7one1
            dmstwonerslbqeighthd2
            2eight3ninefivethreexlvns
            583two
            sixtwo589six22mbsq
            943rljkqfour
            oneone946xnjsgsix
            1eight6mlvbgqqtpr1nine8one
            4eightqz32onefivexnrjlgdkv
            6qhbxgvvt4fivesevenfour
            zlnztmlb345
            1ninelsrrtzznvvtwopbc
            ones3sixgtgvlnvn3
            xnpqnsjmg7jggkqsznhrg
            smqtvjmsczbxqqcninejfone6
            1three38qqvjnqjn5
            8onengjzrfeightgseventwo
            9brrvfoursevendlfhgdl6
            vkxtzgmttgtmgonethmbvltlz7
            k7eightfoureight8bjfch
            two5j9
            five94five47
            twoxtzdgvmghrfphbbonellzkmeight14
            9hzrxd7
            sixvtrpshzpxd7fthreeone
            fivepzvm5
            54hsbrxzg9two2gkdghhp
            3dxqhsmh
            dsrxdpfpt16ninezeight2twotklstzd
            sevenbslzfcglrsvqqgdptpcrtbrgb2five9
            ninetsbhxlkzfninebzjx2
            nine8eight6
            51sixclzmqnklzt1ggnqkdmtxszppl1
            ltwonedpgctq99stbsf
            43nine5vxdblnjtthreetwo1mc
            6fourbtbfqvjhrxbtdkj
            kxz12one
            fourtfjvsevenmtsxqzxcznine2
            26b
            nine4mgcgnzrvxsvthreeonettclg
            sevenseven2kfxxoneninefour
            2pxpsclrn32threefivefzvk9five
            veight66jztz1sixnvpqt
            pgrlhz3four
            fourfivefourninefklkdgdznllfgtj4seven1
            sevenk1bn83
            3mmpfkdxtnvqfvnfivepmvmpsvhrrthreelzjbkgsrthree
            fourfivefourfivegkjpmjh917
            1fivernjglptrkfqhnjzninejmqsevenone
            lphthree8
            nxqmkcg3
            sfsfdtseven9five1eightvcxzpzhqtc
            5twosevenonefninefourzrbp
            5spzsrnhlnthree
            sixnine7fiveqtwo8one
            55fourtwonineonevjrdlqsone
            44vtwo2ninesixcxrjzjs9
            qtxzfsdkmntckpthm2xpdb1fdn
            nqzg1jch68kfdqjgdkfggvcgnmgstnine
            555
            7threehhqdrgc
            25six64eight
            5four3sixthreethreefive3nine
            5twongscdpljqxhtchgfvdhzmdsqkdn
            ljggbcbpk3
            4fourbdnxshzz
            xdqczclsix8bmfjxdcdvmmkcscnjv72six
            2seven3nine77two
            twoeight7nststfmgzccv9tmrnk
            fivesrn7mvcnqkjlxbfivepzzctwo
            1threerxzgffour3qcmtdshj
            86lqf6
            jzclbmfj9zckkjpcdjxnkftrbfcqgdfmzvpdsjpfqp5
            p9ntljjnzrqjlqfrdrkjbbkfourseven
            ssggbzchdsixeight7fivefive
            326one
            62three3p2rhmgvpeight4
            29one2kr8
            xvrjgx5oneseven86eightrhlgpc
            7eightfm6two
            fqdj5four4twordncvxqsix
            3rctxbzfvnpgkdbfour5oneightcfq
            fhbxgrdhtctfour5sixnflszkffour5
            ndlmzp1nineninec
            7fiveljkjlhmfthree5five2three
            13nine9one
            xmcstmztxvxmrsixxzp8xdgzh3
            sconeightvnznvjxpxhsix7seven
            two485seventhreeggkcrjtzg5
            7vpsevencqlnftsx6hx
            six2kfjcgcnnjs4
            ljlscdjmjxlhqcsevenfourmxfour9
            eightfivelxfthreekcjzxkm9vk
            498vnxmgphh9one
            ninenine85
            6twonenhp
            9tgnx
            ninexvrsmrphjpkbfvjqssevenfive8qgsjgnfrltm
            8pcsbl52
            five5klggkskxcthree32
            hjctwo6jvhmsqgsz
            7sgzh82vpvtjtqjlmfnnpbl
            3pdheight2two7tdqzjzdfive3
            pcptskpgsvx63twothgtbg5lmfbmkq
            sz6three6fourfour8seven
            6fourpgfvsjsrjmdninehjvsixtvxzj
            six3one71fourthreebzgjsxkptq5
            two531njqjsxvtsixckkfggxmkz
            93nine1ninemq4mlxprdrcs7
            736sixhsboneninefzfnhxhql
            jvqmrv32
            2threetwo3twosxs
            fiveeight2sixtmmttfbhtktgxgrvtk
            sevensixbpqx2fivevvkpkmlxrdxqtzb
            nineljsnjgzzd6
            two3seven
            dfour4fourqrqxmrmqlzfzzrmd
            26two4jkcjczoneeightpxb
            twosmfpjqlhlkdqtjqvqslkzhx8threetwonek
            twojbt3
            eight6ninexhztllsnineonefive5
            jxscfpmcfour2ksmgthreevbtvzxxrqmtznqqcjzghv5
            5zsmdlsevenninethree
            vnnmbgxjdzdcrhpm22nines
            7sevendzsxrboneone
            one19njgspzjvpllfcznbqvthreetjmzbhtmzknfrqf
            sixsixsixhqn22six1bdckxsfj
            xsevenseven65n
            twohbfvzvkpzjrmzdreightmrrkl7eight
            pxtvbrlqbztqxhvcrrf55
            qtwogljlgnz53
            seven2ninetfzzsmgzfour9twomgzjnnbdfv
            mptlphqjnpbbjbj3dgpvnttseventrmjlnpbxgkbchsszh
            5qnhsjdtc9one7
            vsdgvjqvfivefour3qmvfxmdfn5gkkthrsfnq
            2zqhrl67three6
            52six
            t7one5gcksv41ftknzgdpskrtwonekf
            ngqfhbvonesix49six4ck
            gjcclbdp2twothreejlgcmmvdldpnfnfdhcdrgxeight
            sixpvzhqtzt2five
            fivefourh6zhfbtlcfvfsvbtcrone5
            threehjdl27sevenftfjpnpninethree
            seven2qffourtnbrtslmbrtzrbss
            5nine3onesix
            mpkksrpzpcnjceightcrfsjhl9rfmxkhzkvpnvzck
            rlqxvhcmfiverqlp86threesixnineseven
            two9three
            1hrqsrsqk
            s8jvshkhmsbjnnzzvzl1threefour5two
            hdq6bvlrtwo
            2mfzlkfflfive
            42nine176
            2onemgfxnxbsvr4
            six5onegkvfxktggkrmnjhsevenfournjbm
            onetscccxrrbd5fourqlvqtfknr3
            twonine5
            zftwoeight3six815four
            1oneightq
            seven8nineninegjstgcs1
            sixonefive996
            57qr7p59kx
            vkvfqlrdcnlsfour2tcjc8nine
            bbxsstbrnmnine99zcpp2
            6psfcj7fiveoned9pxk1
            stndz8q9
            7seven1gcmvm2zlflone
            sixmmkconefznkdmhfivetwobfbrlvfive6
            7qblvlssb
            bxlpnjhkvx1bzjgj5515four
            sevenjnine2
            hqrmgtbhlrbchqld6fh6oneseven4ssfkhm
            3zpjqqgtk9
            4zr8pckql23mlsfvr1
            8zpdxmbdj
            vdtgghlr9dqlkcbtjcptqftone2
            7phhnj1hvnpznone
            seven1three
            bvzqkpmxlplv4threezjkpbbngtgfxslhczq
            eight353fourjbthbjzrxk3
            1vszpmreightqkcbpmgvz
            two8gdjbjnsix5mpcjd
            ngtltwo8cvdhtpnine
            eight7seven57four234
            316xqvstdslj
            three2dxhg4one87mblcfljtrhoneightb
            seventhree1646fivefour
            3twojplhronenine
            6fpv43gnzm
            qncbbfkfceightngdnxkllfourone6
            seven238
            76vdsxpfngdpslhkh8
            two86
            5zfmlbpseven
            8twosix
            fiverg414vvlxccjx6
            7vbptbclkkrlnt24threeseven
            four36crrfour29
            sixlfivelqqmbmcxvfkpdfpfzsix39nine
            smmfd9vczg2mhsjdnvgtnine
            threefgxqhtrln7scjxkndonetwofive2khzx
            4threejtrblf3774nzndnnjvf
            cthree9bzd65
            869foureight
            6jknfthxptgjlzqjznjpbthz
            mmhgmpncgctwoztseven8th2fncrcvmckxxhxzmqxl
            9zmmt6sevenfive
            fnstwonensgnkrrgrm333onetsckzm2
            1five6eightnine1five1seven
            1tjvvbtsbr1four
            eightsevenfivesixthreefnlgzpkffive3
            4eightmfjxkhddrxrttzffdnthree
            9seightone
            dfcscfour8
            58vfjhqsz44dktkdbcggvzrxonesqqqfzmp
            fivefourhghrmjbtrdseven7five7tdlmtxhv
            qjcjcthreefive64
            ppcndxtgzqsqfqq9sixonefourrkf
            f5chjf9seventwosix4
            tmtzhrbzqbtsfbbkr25rqv6pgz
            3svvptrmnxgsqpgppvseventhreesix1
            d5fivessix
            8br
            834fivemrhnnjmbf
            eightlzbtqrrfvxhhnx6vbxvzdt2eightkz2
            foursix51lfnpbkczk8twosixeight
            2nzjhljckdfiveeightfive
            lxftlsgrrsrgqntqxcbnp3threeseven
            lhpzv3hkcsbhnq7
            kdfzdtxlqbfive9
            threedhqseven8eightseven
            7tbtlpsmzlbkfzcdbmndfvgcxcqtfzmdhqktwo5
            krhqqrsixseven72jxhgchzgcmtrthtqnmmnpttc
            1xxfvpvlzjfive8fivefivekjfsq
            five13
            1fivexgsgk785vmdlgf
            14fourbrg65
            1onesmprhxsone1fourkt
            twoclk8
            fivenine4d
            six3seven6
            svcmfmqfsevenonesevenbhsrptwo2
            4onefivevznngdbffour5scjxpftpnhtwo
            vvmgdzrfldhsjflseven5eight54eightnine
            jpc54
            threenine65fivekvms
            eightfsixnineseven2three
            three43fcfrbmpmgthreerpxshkfcfoneeight
            8jmvvgmltvzlqjonevdtdpvngtwocxcthree
            dlxth8
            two58
            6k5sixseven
            vfkpzmrxlcfhj7szfcpprjbdlzltmkkmj
            8ltv4
            1twofkcpppq
            five2sixfour6dthpckzrzxspjptdxxkfmn
            3eightdkkone2onex54
            fouronerpn5sevennine4three
            2gjbxsgsgt3nczpbdf
            4ngvcffpgfx9hzxkvmsfsevensix1
            5smqrqkseven4ttjrnnjfsh3gbfkgfivesc
            sevensixsevennine5f48six
            2fourdmdone
            fourtwo5zsrhxmjkn43
            bchrtcnltseven6
            vtwone9dfeightfiveghzbgonenlckrg
            1twothree22
            twoqtt58threeklrpkhlc3
            bnmq4fivegc
            dqffiveninenine4tnsrk375
            pgeightwo6tsevenseven9seventwomnfcvglsf
            27eightpbbkn9zzldxqplzjthreetwo
            vg6
            lnsnglzsm96nine8
            one2bgmmnvnbtlxmnbjk3vvtqlqtwothreeeightwokbd
            9three8two
            fgxfbthree8mbpplseven3one
            four346sevenjxrgkxf2
            three5qjsevenninetwo
            oneseven4497one1rmkljk
            cvfcmsqcscphlseven6four
            threeeightsvsps3
            3pcqgqkcpgnzlkxnneightonehzgsix
            mpeightwosrfpfq2bztpmhx
            4fourtwoqfbhrlzjmcc
            sixtwokm4tpcsfour
            58one
            pdbpzhptjfhfive4nine6
            3three3threedqlmbonethree99
            7fhldqcxbdlbdggtm9six98
            g1one22fiveone1slpzmtcn
            sevensixbmpg66four7cqcfxg1
            lvpcjshtwotvdnxkqzv4
            4tbmmgqxdsr54seventhree8four
            99oneonedhvr1
            3tfszvjgrbf
            one2nineztqchrz
            bfnnlh5nnzltmqlx9
            gcsmzxlvssix5lkmk1
            hv4eight5
            8clgggvkkcj3
            pmkvsixfive2
            5lk5
            8four7qeight
            xxfgflhgqnine96gdqcdqlrhfive
            vpdmcz1223seven3
            gt7fivenineeightchbfrvbtl2foursix
            eight86qtm5
            4rzcxqz4five8fhdfmdksthree7eightwom
            fourtwo3three2
            38ninezlzdp8fourqrgjc
            1959
            8mvnhpvkddhtwonghtlpq
            dbd2xttfllfp2
            23eightseven
            53nine77qzbsrpgsg
            xbd4onethreechblfshmeight
            837sixbxtjfivetprgjjd
            62six
            1fivegfklmmqnkrdnvrpjpcnlzbveight
            five632pqcksmxlrznqvjcmlttqhh
            twoeight9jgmjxpjjxjhxz
            pmt83fourone7sevensix
            578twonmpvzzvd7
            six9six
            2xczcgszvrl25fivefourgvkhtcfhqmbggrneighteightwopqt
            5oneseven1
            seven3onexg
            sixpvrtxqlmrqbz9five89seven
            8eightp2six
            3thtzqgpzkdvfivenine
            17942twonebcl
            ninevfcseven34n5
            five3dt3
            three6knn
            jsmmgrjsix5zqsnbfmgjlmrptqzvzmjr7brm9
            3ll7992243
            8fourfonemhtmkrht8rhfdljqb3five
            threefourgxbvvrfourtqnrf7
            3v27nine
            3lklpsnf
            39sixjpdbd7sevenhc
            oneone1
            9vft
            2pnrfourbhv5frsgpjfnh
            7zqqfldgq64eight84vsbrdhscnc
            5q4kxgjnxoneninethree
            threemllvrszngnthreenine6
            3ninegkshrtnlccsixeightsixf6
            5two47
            9jnkpcfnxeight674brdflkbd
            66sixsixzpgq
            onesvcmtlhrrslxsktjfbkz9eight58
            vprnflbkbnsfsmfkfrmrdfbjhk4tworqsbonethree
            7onetwogmskgscbtxeightsqgfvvc4mlttvhgt
            7nine6141t4pqlgvc
            knbvjgvzpm3pztzzqmntxcgvjn
            tprneight9qxzqslmm7fivefmxtxn
            6xvvjbcdfoursixone5eightxztjztzpgsix
            24six4s6sixnine
            oneqlppjrfqsevenhgqlxkdhqx8s
            8nineeightc
            jbconeoneeight9frgxlrdhzlcrzshjknxmfrmqmh
            561nine8zhxlhcrmdj
            5qtmn17ninephlnsqpts4two
            nznsfgmddt1nine
            gsmqltczf6qpm
            fourfmhl8bvgv
            six8blfivefour
            one6fjm
            tvsdnjt72five3seveneight
            7threekkxlzcgrfvltwo
            mtxeight1one443six
            pgsfz6sevenfour
            13mfzcpgtbzsdcbqtmhlll
            mftjkvxqpninefjbone1f
            sixtgrfmjl97fsjhgcsmmp4
            cqsksrkqqp7cfvjkrptsjdsk3
            xhphdkvtjzgkmvbj1fourj1sixone
            foureight86three4one4
            5threep7kfkznmhj28
            sfvpqfivem9fiveone
            912fourztbndhpbzcbc
            mbvpzgeightseven48eight
            mfpq528cvgxnv7
            xbeightwo78five
            kqngfmq5sevenddchr9
            4threevvt8
            hpxsdjdksix2
            2r
            9four32four
            8twotqhxgj47
            6sixsfgbheighth
            njmljrmtsevensix21phkczzeight6seven
            twomfklfdqvmbdnptfssrrjdfour9
            6five3
            tjzqzdmkj2qt13
            twocjncqgm7one5sixfour
            fivefourjtvqxchf5cgf79kmlcsd5
            7nsrjmcleight1threenine
            lvstq3tn1
            twotwo44
            noneight5msix2qdpb87g
            9rp3dtp21four2three
            5fouronejmpdq2two7nine
            poneighthvhkbvtvxzmljfhrmf3fourbdcssgjtbht
            sevenninef5
            eight9oneeightsix9three
            eighttwo841
            6nine45ninetwo1
            bsrtmkplhbjbrsbjsrzpbfxpvj87onepkc
            9fivephcqlsln9
            xftntmjsb168rjjvv5five8
            frfr628vrbrzg
            kqgz3jms56six83six
            threefourfourtwo2p
            rrxdjjgrfmkzqrqtdg7onefour
            oneksqkxk8315cjtxhpl
            pmfmf7six2
            xfdmvhmrfbfour7
            36sevenone
            nine25bfivedhgch1
            cpvrs97twod3
            fivesix23six5
            95pkmbpphdmninenthphrzknhgvzfour
            5mqlkhqfzfive3bscfcnine
            8eightvmbhccqkvmpsnjxxfourpng
            twok4
            4one8dlrtdgbkxthreetwo7
            4spvdztldx93twor93one
            cfl7
            vgfzjqhfvz7onefivevkqvrxtkmone
            7fourtwofour
            fivemscxhgtrcsone2jjr1
            htbrrlrgkvgpbhcqtn5ninefournine2x
            bttjgdnnmlrljsltxrbzjqhnbfghcktt5fivebfqbgsvt
            sixfivefive977
            bqzqdh38ninesix
            hmghtzdgqnlh7
            threenine4
            5sevenseven
            5qphzsdgsxhnfivesixeight
            5zhdfpvk6seventhreenine
            jrtrsevenfour9seven
            kzvlczdmvlmlnjqrbgjdtwosix8rqhjjkffz
            bmjmv869
            four7three
            six1five34
            7zchsmjgqnqffzjktfourcchdhzxjmsix
            5cprgcs5bvjqgkqrqc2
            fiveeight5gkk
            14nine2eightxrqqlthree98
            four5nine5nzgrrgqpnkpkbone
            2foursevenqzqgzone
            oneqjcplbtdqcfourmktsjbjhgeight4
            9nine7four6
            dqcjqvmmhxthree2seven
            11bvnrsbkgtzlrvhdhqk
            fiveseveneight9twosmcmcmmdx8eight
            seven8qvgppnbkggrjlqthree
            83ntsixvdvkhlzseven
            7eighthfxgnqclk
            sevengxzone4fivexcfffdxg7pjxjbb8
            eight84eight46
            52clgxx3npppzfournqtbpvsnvjpteightwomss
            tctwone4dbqvnvf
            pfsgbkfivetworpqtbbfs38nineoneightjg
            37eightfourthree
            1eight2ndctgkcbmjmthree
            vmcxzmmzvk63fllrlg4one
            cmjpxqkmfouronenine3twojplnvfrncone
            seveneightpjdbx3twoeight7
            143klgccxcmcteight172xgqlmmkfjp
            fbrhrjcmljtsfqhtms614threeninendjjzpdpzp
            8hrtbrvxssixtwo6four4vgttssxthree
            szfr671
            73njhhjpxlflhmdqrtzgm3
            gglrxone97four83nine
            rz8
            3lxvdvgvsnmhfqcmgrqgf7njqfphdnxj
            eightsix9crbjrtcvlzfive3rffgxsbcgqpxdczmv
            two4kfqbcrz7eightfsdfzrreight
            2sevenmcxcqpbpjgoneseventhree
            one4four6lphcmhnd
            79
            dnnzzhqthtwosevenlfgxbzlxnclzbksevenh6eight
            twoseventwoqgjbjvxfmsmnslrbbhvn8nine
            13jtmqsdninetkf
            three7fourseveneightngbrsgkrtfltffksevenr
            jlpm5fourxkrldrzbmjbddptd
            four91sevenone1lkbfxhl
            gnqsbnck1two
            19nine3five7tkzjfour
            vqjg22four9eight
            1threeeight7cn
            3vnxjcc5threefiveone5
            fiveltjstjtpr76zsixjvkkb
            xbdnrjgplmrnfn59two6mfhsphtsmgxgpzsp
            4jb39ninebfr
            jz1xpbtspgb7
            fivesixsldbpmjpnkxlfour1l
            stvdtqtrrtdvplxtfjlbnbs9oneg7three
            two9eight7four
            nine395five3
            2zl23threegrqdbkfourkfqh1
            1mzdslsbsevencccphxpdbtzqj1
            6nhtfsmqfdssix67
            sixeightdhbvdnrb2six2
            1sdqrzsfr113ninertcrbhthreekrttgbj
            lone9xdhfive48
            xhgqjgcdneight2
            ninefouronez14srhgxtqd
            dzvbvpronenineb3nine
            kczhrrthreesixeightbhcccxlqkj2djfqrlfc
            five5five2ksgn
            threefourjfqzlgtvv8pfx31v
            1fivejlvfqjhsm
            four6972fournzmpchninegddxrfh
            12nine
            2nrsgtdlddhqdxhqpljtfourppeight
            bbqrgsxlgptbqplhs8pkone
            5nineztvqdnine744
            tsrkfddkc1three
            9sixone9nfbksgbfpfour24three
            98lztb
            oneeightfnhtkkhfqtpfpdhqjbpflhhzf463
            one9kmhghftf7qsmztntzx92
            xzzpjmqlxnknk543vdfgldvfdrbnnfttmtqzfh
            dq74twosixfourfiverfbxzvjtfv5
            lqrnlzvxngfcsgg9dmpjjlgfm6two
            ninejhxqrtfrstwo22dxnvfmtbscrkzc
            txjeightwo21
            qlsix459ttqtsfzg
            bxbeight15fktzb3vjxbnkck6
            53twohbrqgtd
            mfztbczrqltfivesix8
            9lxtnpzffczvhssfb
            fivehtzrxsrm19
            1ctslhhtwo1
            five6zsix1gbjhklpszlkmszcj
            4shtgrkninevqntzltrhhflgrhsslz9one
            four3tsk45
            hpkt8eightsix
            5nine9two
            fourxvkjdkdgbklcnzmhqtgdq1
            1foursxbrk43mzfjxqrsloneseven
            lsbhfoursix9sevenone7
            vtnxseight1ndlxgleighttwosixthree
            5gsfbrqtfkz
            sixonetpfnznvrnxhgcqkv6nine
            3rhbcklhbtqljqheight7
            zhtkqb1rsntcgnq85gftptxqc8
            shhmqrgdpvscvkcdpks4
            nineninenine2three5two3
            trfbhljnl9nine
            sixlcmnpkdlqr6
            qhdrmdjbmczfdjqfxv7eightwoxt
            n4nkjlfx8seveneight
            46eightgjnhsx
            mbeightwo4jbcgxprkjrjspf
            ninemq9sixvrdqmr
            fourkxvmmdf87pfzjfdpthreex
            9ninebp1
            1four2kghfdgtgdx
            5hrlslhpnine4seven4bzjttvtv4
            xcqqrxdkdsckqtnvlqh9three
            8sixnine
            9fourthreesixzglsqrmgtt8
            2onetwo
            98kgnrkdvjfeightfive5tkffmq
            7mrjskhffjqeightfiveoneonethree
            7lgfcqvthree8bvlk2kf4
            2xstvqxnsggkxvfrllzkseven9zqtwo
            xjqcvvh6two
            2seven8gjdgmg
            ghvzpxlgfhctwortxvlfkxl1tqkthbddfhpzdjklk3
            nineninernvpzsbnine2ninenbrkbkdclc6
            29twojsvrz7eightzhznhflcld71
            eightthreefive5kkgcconepmm
            3tlz7fjzpgnvfgdgqbrqqsv
            mcp213six4jjqtgxpkzh
            one7nineqnjsgcjnjmdhdrxbthree
            xzvxcvpdftpllcxvpbtwoxkspeightvmfhlqxklthree1
            nnzksztgtpfour61mfjmvnonezgvhdg
            qkj6one5one49g7
            threesix6eightlxdkttbbcz3bbvgnxjnjf
            3fivenine
            3sixhmbnfrk
            7qcjzchtkssix
            vsfour5nqjjzmx
            cdxhcsrhvc3
            47hjzgbpsixjjfsbhninerfbrvf
            seven8sevenptdlvvgssixvjvzpvsp7fivefourtwoned
            ctwonezxpflzeightczjnvj7173eight
            ldklqc91fiveeighteight9
            pptwo6sevenfivevlgs8threegbpc
            ctwoneone7dtztzrk3ninefivexvj
            1oneninegspfm3four43
            sixfourgkdlxtqmbzkgmpmcsevenhzrt4
            eight6twojtzlvlhgjncvx
            """;
}